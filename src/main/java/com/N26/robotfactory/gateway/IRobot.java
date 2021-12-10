package com.N26.robotfactory.gateway;

public interface IRobot {
    Double findPrice (String productCode);
    void updateStock (String componentCode);

}
