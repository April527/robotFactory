package com.N26.robotfactory.domain.usecase;

import com.N26.robotfactory.RobotFactory;
import com.N26.robotfactory.domain.model.BusinessException;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.domain.model.ResponseRobotFactory;
import com.N26.robotfactory.gateway.IRobot;
import com.N26.robotfactory.gateway.IStock;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
public class RobotUseCase {

    private RobotFactory robotFactory;
    private final IStock stockRepository;

    public static final String NON_AVAILABLE_OR_NON_EXISTENT_COMPONENT = "At least one component doesn't exist or it's not available";

    public static final String INVALID_ORDER = "There must be only one of each valid component in the order, and in the correct sequence";

    final String MATERIAL = "MATERIAL";
    final String FACE = "FACE";
    final String ARM = "HANDS";
    final String MOBILITY = "MOBILITY";

     public Mono<ResponseEntity> robotOrder(List<String> components) {

         List<List<String>> componentsList = setComponentsList(components);

         return validateRobotOrder(getCurrentStock(), componentsList) ? placeRobotOrder(getCurrentStock(), componentsList)
                 : buildInvalidOrderResponse();

    }

    private Mono<ResponseEntity> placeRobotOrder(List<ComponentInventory> componentInventory, List<List<String>> componentsList) {

        return updateStock(componentInventory, componentsList)
                .flatMap(componentInventoryList -> findRobotItemsPrice(componentInventoryList, componentsList))
                .reduce(new BigDecimal(0), BigDecimal::add)
                .map(totalRobotPrice -> totalRobotPrice.setScale(2, RoundingMode.HALF_UP))
                .map(this::buildRobotResponse);
    }

    private List<ComponentInventory> getCurrentStock() {

      return  stockRepository.getStock().isEmpty()? stockRepository.setStock(): stockRepository.getStock() ;
    }


    private Mono<ResponseEntity> buildInvalidOrderResponse() {
        return Mono.just(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new BusinessException(INVALID_ORDER)));
    }

    private boolean validateRobotOrder(List<ComponentInventory> componentInventory1, List<List<String>> componentsList) {

        List<List<String>> robotOrderList = componentsList.stream()
                .filter(component ->
                        componentInventory1.stream()
                                .anyMatch(compInventory1 -> compInventory1.getCode().equals(component.get(1)) &&
                                        compInventory1.getPart().toLowerCase().contains(component.get(0).toLowerCase())))
                .collect(Collectors.toList());

        return robotOrderList.equals(componentsList);
    }

    private String generateOrder_Id(){
        return UUID.randomUUID().toString();
    }

    private ResponseEntity buildRobotResponse(BigDecimal total) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseRobotFactory.builder()
                .order_id(generateOrder_Id())
                .total(total)
                .build());
    }

    private List<List<String>> setComponentsList(List<String> components) {

        List<String> componentsName = Arrays.asList(MATERIAL,FACE,ARM, MOBILITY);

        return IntStream.range(0, componentsName.size())
                .boxed()
                .flatMap(i -> {
                    return Arrays.asList(Arrays.asList(componentsName.get(i), components.get(i))).stream();
                })
                .collect(Collectors.toList());

    }

    public Flux<ComponentInventory> updateStock(List<ComponentInventory> componentInventory, List<List<String>> pairedComponents) {

        return Flux.fromIterable(pairedComponents)
                .filterWhen(component -> isComponentAvailable(componentInventory, component, pairedComponents))
                .map(this::updateRobotStock)
                .flatMap(componentInventory1 ->componentInventory1)
                .onErrorResume(err -> Flux.error(new BusinessException(NON_AVAILABLE_OR_NON_EXISTENT_COMPONENT)));

    }

    private Flux<ComponentInventory> updateRobotStock(List<String> robotComponent) {

        IRobot robotPart = robotFactory.getRobotParts(robotComponent.get(0));

        return robotPart.updateStock(robotComponent.get(1));
    }


    private Mono<Boolean> isComponentAvailable(List<ComponentInventory> componentInventory, List<String> component,
                                               List<List<String>> pairedComponents) {

        IRobot robotPart = robotFactory.getRobotParts(component.get(0));

        return robotPart.findRobotPart(componentInventory, component.get(1))
                .filter(componentInventory1 -> !(componentInventory1.getAvailable() > 0))
                .flatMap(componentInventory2 -> executeInventoryRollback(componentInventory2.getCode(), pairedComponents, componentInventory))
                .switchIfEmpty(Mono.just(true));
    }

    private Mono<Boolean> executeInventoryRollback(String componentCode, List<List<String>> pairedComponents, List<ComponentInventory> componentInventory) {

        return stockRepository.rollbackInventory(componentCode, pairedComponents, componentInventory)
                .then(Mono.error(new BusinessException(NON_AVAILABLE_OR_NON_EXISTENT_COMPONENT)));

    }

    private Mono<BigDecimal> findRobotItemsPrice(ComponentInventory componentInventory, List<List<String>> pairedComponents) {

        return Flux.fromIterable(pairedComponents)
                .flatMap(pairedComponentList -> findRobotPart(componentInventory,pairedComponentList))
                .map(ComponentInventory::getPrice)
                .reduce(new BigDecimal(0), BigDecimal::add)
                .map(totalRobotPrice -> totalRobotPrice.setScale(2, RoundingMode.HALF_UP));

    }

    private Mono<ComponentInventory> findRobotPart(ComponentInventory componentInventory, List<String> componentName) {

        return (componentInventory.getCode().equals(componentName.get(1))) ? Mono.just(componentInventory):
                Mono.just(ComponentInventory.builder()
                        .price(new BigDecimal(0))
                        .build());

    }

}
