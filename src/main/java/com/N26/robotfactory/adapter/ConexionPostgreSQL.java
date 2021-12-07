package com.N26.robotfactory.adapter;

import com.N26.robotfactory.gateway.IConexion;

public class ConexionPostgreSQL implements IConexion {
    @Override
    public void conectar() {
        System.out.println("Se conecto a PostgreSQL");
    }

    @Override
    public void desconectar() {
        System.out.println("Se desconecto de PostgreSQL");
    }
}
