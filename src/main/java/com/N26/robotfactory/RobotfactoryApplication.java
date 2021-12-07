package com.N26.robotfactory;

import com.N26.robotfactory.gateway.IConexion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RobotfactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RobotfactoryApplication.class, args);

		ConexionFabrica fabrica = new ConexionFabrica();

		IConexion cx1 = fabrica.getConexion("ORACLE");
		cx1.conectar();
		cx1.desconectar();

		IConexion cx2 = fabrica.getConexion("MYSQL");
		cx2.conectar();
		cx2.desconectar();

		IConexion cx3 = fabrica.getConexion("H3");
		cx3.conectar();
		cx3.desconectar();

	}

}
