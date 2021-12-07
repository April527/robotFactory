package com.N26.robotfactory.adapter;

import com.N26.robotfactory.adapter.Dog;
import com.N26.robotfactory.adapter.Duck;
import com.N26.robotfactory.gateway.AbstractFactory;
import com.N26.robotfactory.gateway.Animal;

public class AnimalFactory implements AbstractFactory<Animal> {
    @Override
    public Animal create(String animalType) {
        if ("Dog".equalsIgnoreCase(animalType)) {
            return new Dog();
        } else if ("Duck".equalsIgnoreCase(animalType)) {
            return new Duck();
        }

        return null;
    }
}
