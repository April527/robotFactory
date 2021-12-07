package com.N26.robotfactory;

import com.N26.robotfactory.gateway.IRobot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RobotfactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RobotfactoryApplication.class, args);

	/*	RobotFactory fabrica = new RobotFactory();

		IRobot cx1 = fabrica.getConexion("ORACLE");
		cx1.findPrice();
		cx1.updateStock();

		IRobot cx2 = fabrica.getConexion("MYSQL");
		cx2.findPrice();
		cx2.updateStock();

		IRobot cx3 = fabrica.getConexion("H3");
		cx3.findPrice();
		cx3.updateStock();*/

	}

}
