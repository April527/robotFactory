package com.N26.robotfactory.domain.usecase;

import com.N26.robotfactory.domain.model.BusinessException;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.domain.model.ResponseRobotFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private Stock stock;

    public static final String INVALID_ORDER = "There must be only one of each valid component in the order, and in the correct sequence";

    final String MATERIAL = "MATERIAL";
    final String FACE = "FACE";
    final String ARM = "HANDS";
    final String MOBILITY = "MOBILITY";

     public Mono<ResponseEntity> robotOrder(List<String> components) {

         List<List<String>> componentsList = setComponentsList(components);

         return ValidateRobotOrder.validateRobotOrder(stock.getCurrentStock(), componentsList) ?
                 placeRobotOrder(stock.getCurrentStock(), componentsList)
                 : buildInvalidOrderResponse();

    }

    private Mono<ResponseEntity> placeRobotOrder(List<ComponentInventory> componentInventory, List<List<String>> componentsList) {

        return stock.updateStock(componentInventory, componentsList)
                .flatMap(componentInventoryList -> RobotPrice.findRobotItemsPrice(componentInventoryList, componentsList))
                .reduce(new BigDecimal(0), BigDecimal::add)
                .map(totalRobotPrice -> totalRobotPrice.setScale(2, RoundingMode.HALF_UP))
                .map(this::buildRobotResponse);
    }

    private Mono<ResponseEntity> buildInvalidOrderResponse() {
        return Mono.just(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new BusinessException(INVALID_ORDER)));
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




}
