package com.N26.robotfactory.adapter;

import com.N26.robotfactory.adapter.Purple;
import com.N26.robotfactory.adapter.Red;
import com.N26.robotfactory.gateway.AbstractFactory;
import com.N26.robotfactory.gateway.ColorExample;

public class ColorFactory implements AbstractFactory<ColorExample> {
    @Override
    public ColorExample create(String color) {

        if ("Purple".equalsIgnoreCase(color)) {
            return new Purple();
        } else if ("Red".equalsIgnoreCase(color)) {
            return new Red();
        }

        return null;
    }
}
