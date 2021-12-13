package com.N26.robotfactory.gateway;

import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.domain.model.PairedComponent;

import java.util.List;

public interface IRobot {
    Double findPrice (List<ComponentInventory> componentInventory, String productCode);
    List<ComponentInventory> setStock();
    void updateStock( List<ComponentInventory> componentInventory, List<String> components);

}
