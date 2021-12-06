package com.N26.robotfactory.gateway;

public interface AbstractFactory<T>{
    T create (String animalType);
}
