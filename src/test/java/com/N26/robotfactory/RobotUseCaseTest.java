package com.N26.robotfactory;

import com.N26.robotfactory.adapter.RobotArm;
import com.N26.robotfactory.adapter.RobotFace;
import com.N26.robotfactory.adapter.RobotMaterial;
import com.N26.robotfactory.adapter.RobotMobility;
import com.N26.robotfactory.data.StockRepository;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.domain.usecase.RobotUseCase;
import com.N26.robotfactory.gateway.IRobot;
import com.N26.robotfactory.gateway.IStock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class RobotUseCaseTest {

    private RobotUseCase robotUseCaseTest;

    @Mock
    RobotFactory mockRobotFactory;

    @Mock
    IStock mockIStock;

    @Mock
    private StockRepository mockStockRepository;

    @Mock
    IRobot mockIRobot;

    final String MATERIAL = "MATERIAL";
    final String FACE = "FACE";
    final String ARM = "HANDS";
    final String MOBILITY = "MOBILITY";

    @Before
    public void setUp(){
        initMocks(this);
        robotUseCaseTest = new RobotUseCase(mockRobotFactory, mockIStock);
    }

    @Test
    public void testDecreasesStockAccordingly() {

        ComponentInventory componentInventory = ComponentInventory.builder()
                .code("A")
                .part("FACE")
                .available(2)
                .price(new BigDecimal(10.28).setScale(2, RoundingMode.HALF_UP))
                .build();

        Mockito.when(mockRobotFactory.getRobotParts(FACE)).thenReturn(new RobotFace(mockStockRepository));
        Mockito.when(mockRobotFactory.getRobotParts(MATERIAL)).thenReturn(new RobotMaterial(mockStockRepository));
        Mockito.when(mockRobotFactory.getRobotParts(ARM)).thenReturn(new RobotArm(mockStockRepository));
        Mockito.when(mockRobotFactory.getRobotParts(MOBILITY)).thenReturn(new RobotMobility(mockStockRepository));

        Mockito.when(mockStockRepository.updateRobotPartsStock(ArgumentMatchers.anyString())).thenReturn(Flux.fromIterable(setUpdatedRobotPartStock()));
        Mockito.when(mockIRobot.findRobotPart(ArgumentMatchers.anyList(), ArgumentMatchers.anyString())).thenReturn(Mono.just(componentInventory)); //TODO Mock this method well

        Mono<List<ComponentInventory>> result = robotUseCaseTest.updateStock(setRobotPartStock(), buildComponentList());

        assertThat(result.block()).equals(setUpdatedRobotPartStock());

    }

    private List<ComponentInventory> setRobotPartStock() {

        List<ComponentInventory> robotPartStocks = new ArrayList<>();

        robotPartStocks.add(new ComponentInventory("A", new BigDecimal(10.28).setScale(2, RoundingMode.HALF_UP), 9, "Humanoid Face"));
        robotPartStocks.add(new ComponentInventory("B", new BigDecimal(24.07).setScale(2, RoundingMode.HALF_UP), 7, "LCD Face"));
        robotPartStocks.add(new ComponentInventory("C", new BigDecimal(13.30).setScale(2, RoundingMode.HALF_UP), 0, "Steampunk Face"));
        robotPartStocks.add(new ComponentInventory("D", new BigDecimal(28.94).setScale(2, RoundingMode.HALF_UP), 1, "Arms with Hands"));
        robotPartStocks.add(new ComponentInventory("E", new BigDecimal(12.39).setScale(2, RoundingMode.HALF_UP), 3, "Arms with Grippers"));
        robotPartStocks.add(new ComponentInventory("F", new BigDecimal(30.77).setScale(2, RoundingMode.HALF_UP), 2, "Mobility with Wheels"));
        robotPartStocks.add(new ComponentInventory("G", new BigDecimal(55.13).setScale(2, RoundingMode.HALF_UP), 15, "Mobility with Legs"));
        robotPartStocks.add(new ComponentInventory("H", new BigDecimal(50.00).setScale(2, RoundingMode.HALF_UP), 7, "Mobility with Tracks"));
        robotPartStocks.add(new ComponentInventory("I", new BigDecimal(90.12).setScale(2, RoundingMode.HALF_UP), 92, "Material Bioplastic"));
        robotPartStocks.add(new ComponentInventory("J", new BigDecimal(82.31).setScale(2, RoundingMode.HALF_UP), 15, "Material Metallic"));

        return robotPartStocks;
    }

    private List<ComponentInventory> setUpdatedRobotPartStock() {

        List<ComponentInventory> robotPartStocks = new ArrayList<>();

        robotPartStocks.add(new ComponentInventory("A", new BigDecimal(10.28).setScale(2, RoundingMode.HALF_UP), 8, "Humanoid Face"));
        robotPartStocks.add(new ComponentInventory("B", new BigDecimal(24.07).setScale(2, RoundingMode.HALF_UP), 7, "LCD Face"));
        robotPartStocks.add(new ComponentInventory("C", new BigDecimal(13.30).setScale(2, RoundingMode.HALF_UP), 0, "Steampunk Face"));
        robotPartStocks.add(new ComponentInventory("D", new BigDecimal(28.94).setScale(2, RoundingMode.HALF_UP), 0, "Arms with Hands"));
        robotPartStocks.add(new ComponentInventory("E", new BigDecimal(12.39).setScale(2, RoundingMode.HALF_UP), 3, "Arms with Grippers"));
        robotPartStocks.add(new ComponentInventory("F", new BigDecimal(30.77).setScale(2, RoundingMode.HALF_UP), 1, "Mobility with Wheels"));
        robotPartStocks.add(new ComponentInventory("G", new BigDecimal(55.13).setScale(2, RoundingMode.HALF_UP), 15, "Mobility with Legs"));
        robotPartStocks.add(new ComponentInventory("H", new BigDecimal(50.00).setScale(2, RoundingMode.HALF_UP), 7, "Mobility with Tracks"));
        robotPartStocks.add(new ComponentInventory("I", new BigDecimal(90.12).setScale(2, RoundingMode.HALF_UP), 91, "Material Bioplastic"));
        robotPartStocks.add(new ComponentInventory("J", new BigDecimal(82.31).setScale(2, RoundingMode.HALF_UP), 15, "Material Metallic"));

        return robotPartStocks;
    }


    private List<List<String>> buildComponentList() {

        List<String> components = Arrays.asList("I","A","D","F");

        List<String> componentsName = Arrays.asList(MATERIAL,FACE,ARM, MOBILITY);

        return IntStream.range(0, componentsName.size())
                .boxed()
                .flatMap(i -> {
                    return Arrays.asList(Arrays.asList(componentsName.get(i), components.get(i))).stream();
                })
                .collect(Collectors.toList());

    }
}
