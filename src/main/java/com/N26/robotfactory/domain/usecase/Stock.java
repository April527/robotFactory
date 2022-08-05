package com.N26.robotfactory.domain.usecase;

import com.N26.robotfactory.RobotFactory;
import com.N26.robotfactory.domain.model.BusinessException;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.gateway.IRobot;
import com.N26.robotfactory.gateway.IStock;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
public class Stock {

    public static final String NON_AVAILABLE_OR_NON_EXISTENT_COMPONENT = "At least one component doesn't exist or it's not available";

    private RobotFactory robotFactory;

    private final IStock stockRepository;


      Flux<ComponentInventory> updateStock(List<ComponentInventory> componentInventory, List<List<String>> pairedComponents) {

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

      List<ComponentInventory> getCurrentStock() {

        return  stockRepository.getStock().isEmpty()? stockRepository.setStock(): stockRepository.getStock() ;
    }

}
