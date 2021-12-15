package com.N26.robotfactory.gateway;

import com.N26.robotfactory.domain.model.ComponentInventory;

import java.util.List;

public interface IRobot {
    Double findPrice (List<ComponentInventory> componentInventory, String productCode);
    void updateStock( List<ComponentInventory> componentInventory, String componentCode);

}
