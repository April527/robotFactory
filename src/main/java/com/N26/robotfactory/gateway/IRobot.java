package com.N26.robotfactory.gateway;

import com.N26.robotfactory.domain.model.ComponentInventory;

import java.math.BigDecimal;
import java.util.List;

public interface IRobot {
    BigDecimal findPrice (List<ComponentInventory> componentInventory, String productCode);
    void updateStock( List<ComponentInventory> componentInventory, String componentCode);

}
