package com.N26.robotfactory;

import com.N26.robotfactory.adapter.*;
import com.N26.robotfactory.gateway.IConexion;

public class ConexionFabrica {

    public IConexion getConexion(String motor){

        if(motor==null){
            return new ConexionVacia();
        }
        if(motor.equalsIgnoreCase("MYSQL")){
            return new ConexionMySQL();
        }
        if(motor.equalsIgnoreCase("ORACLE")){
            return new ConexionOracle();
        }
        if (motor.equalsIgnoreCase("POSTGRE")){
            return new ConexionPostgreSQL();
        }else if (motor.equalsIgnoreCase("SQL")){
            return new ConexionSQL();
        }

        return new ConexionVacia();

    }
}
