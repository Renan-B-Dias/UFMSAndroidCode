package com.example.renanbenattidias.dbex;

import java.io.Serializable;

/**
 * Created by renanbenattidias on 02/05/18.
 */

public class Employee implements Serializable {

    int id;
    Position position;
    String name;
    Double salary;

    public Employee(int id, Position position, String name, Double salary) {
        this.id = id;
        this.position = position;
        this.name = name;
        this.salary = salary;
    }

    public Employee(int id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Employee(String name, Position position, Double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String toString() {
        if(position != null)
            return " Name: " + name + " Salary: " + salary + " Position: " + position.name;
        return " Name: " + name + " Salary: " + salary;
    }
}
