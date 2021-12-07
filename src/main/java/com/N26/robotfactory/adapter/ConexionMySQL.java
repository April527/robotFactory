package com.N26.robotfactory.adapter;

import com.N26.robotfactory.gateway.IConexion;

public class ConexionMySQL implements IConexion {
    @Override
    public void conectar() {
        System.out.println("Se conecto a MySQL");
    }

    @Override
    public void desconectar() {
        System.out.println("Se desconecto de MySQL");
    }
}
