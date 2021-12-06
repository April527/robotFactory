package com.N26.robotfactory.adapter;

import com.N26.robotfactory.gateway.Animal;

public class Duck implements Animal {

    @Override
    public String getAnimal() {
        return "Duck";
    }

    @Override
    public String makeSound() {
        return "squeks";
    }
}
