package com.N26.robotfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RobotfactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RobotfactoryApplication.class, args);

		AbstractFactory factoryAnimal = FactoryProvider.getFactory("Animal");
		Animal animal = (Animal) factoryAnimal.create("Dog");

		System.out.println(animal.getAnimal());

		AbstractFactory factoryColor = FactoryProvider.getFactory("Color");
		ColorExample colorExample = (ColorExample) factoryColor.create("Red");

		System.out.println(colorExample.getColor());

	}

}
