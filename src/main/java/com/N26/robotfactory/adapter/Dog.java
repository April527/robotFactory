package com.N26.robotfactory.adapter;

import com.N26.robotfactory.gateway.Animal;

public class Dog implements Animal {
    @Override
    public String getAnimal() {
        return "Dog";
    }

    @Override
    public String makeSound() {
        return "Barks";
    }
}
