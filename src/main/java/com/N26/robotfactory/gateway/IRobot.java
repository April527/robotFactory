package com.N26.robotfactory.gateway;

public interface IRobot {
    void findPrice (String productCode);
    void updateStock (String unitNumber);

}
