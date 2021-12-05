package com.N26.robotfactory;

public class Duck implements Animal{

    @Override
    public String getAnimal() {
        return "Duck";
    }

    @Override
    public String makeSound() {
        return "squeks";
    }
}
