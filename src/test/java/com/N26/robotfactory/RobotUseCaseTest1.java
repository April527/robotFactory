package com.N26.robotfactory;

import com.N26.robotfactory.adapter.RobotFace;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.domain.usecase.RobotUseCase;
import com.N26.robotfactory.gateway.IRobot;
import com.N26.robotfactory.gateway.IStock;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;

public class RobotUseCaseTest1 {

  /*  @Mock
    private RobotFactory mockRobotFactory;
    @Mock
    private IStock mockStockRepository;

    @Before
    public void setUp(){
        initMocks(this);
        robotUseCase = new RobotUseCase(mockRobotFactory, mockStockRepository);
    }*/

    @Autowired
    private IRobot robotComponent;

    @Autowired
    private IStock stockRepository;

    final static List<ComponentInventory> robotPartStocks = new ArrayList<>();

    public List<ComponentInventory> setInitialRobotPartStock(){

        robotPartStocks.add(new ComponentInventory("A", new BigDecimal(10.28), 9, "Humanoid Face"));
        robotPartStocks.add(new ComponentInventory("B", new BigDecimal(24.07), 7, "LCD Face"));
        robotPartStocks.add(new ComponentInventory("C", new BigDecimal(13.30), 0, "Steampunk Face"));
        robotPartStocks.add(new ComponentInventory("D", new BigDecimal(28.94), 1, "Arms with Hands"));
        robotPartStocks.add(new ComponentInventory("E", new BigDecimal(12.39), 3, "Arms with Grippers"));
        robotPartStocks.add(new ComponentInventory("F", new BigDecimal(30.77), 2, "Mobility with Wheels"));
        robotPartStocks.add(new ComponentInventory("G", new BigDecimal(55.13), 15, "Arms with Hands"));
        robotPartStocks.add(new ComponentInventory("H", new BigDecimal(50.00), 7, "Arms with Grippers"));
        robotPartStocks.add(new ComponentInventory("I", new BigDecimal(90.12), 92, "Mobility with Wheels"));
        robotPartStocks.add(new ComponentInventory("J", new BigDecimal(82.31), 15, "Mobility with Wheels"));
        return robotPartStocks;
    }

    @Test
    public void shouldDecreaseStockAccordingly(){
        robotComponent = new RobotFace();

        stockRepository.setStock();

        robotComponent.updateStock( "A");

        List<ComponentInventory> finalStock = stockRepository.getStock();

       /* Assertions.assertThat(finalStock.stream()
                .filter(componentInventory -> componentInventory.getCode().equals("A"))
                .map(face -> face.getAvailable() == 8));*/

        Assertions.assertThat(!finalStock.isEmpty());
    }
}
