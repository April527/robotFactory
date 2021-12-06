package com.N26.robotfactory;

import com.N26.robotfactory.adapter.AnimalFactory;
import com.N26.robotfactory.adapter.ColorFactory;
import com.N26.robotfactory.gateway.AbstractFactory;

public class FactoryProvider {

    public static AbstractFactory getFactory(String choice){

        if("Animal".equalsIgnoreCase(choice)){
            return new AnimalFactory();
        }
        else if("Color".equalsIgnoreCase(choice)){
            return new ColorFactory();
        }

        return null;
    }

}
