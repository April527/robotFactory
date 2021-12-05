package com.N26.robotfactory;

public class ColorFactory implements AbstractFactory<ColorExample>{
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
