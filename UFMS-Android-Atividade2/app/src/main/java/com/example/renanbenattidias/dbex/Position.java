package com.example.renanbenattidias.dbex;

import java.io.Serializable;

/**
 * Created by renanbenattidias on 02/05/18.
 */

public class Position implements Serializable {

    int id;
    String name;

    public Position(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Position(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }
}
