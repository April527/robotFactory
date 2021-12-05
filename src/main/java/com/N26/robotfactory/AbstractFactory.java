package com.N26.robotfactory;

public interface AbstractFactory<T>{
    T create (String animalType);
}
