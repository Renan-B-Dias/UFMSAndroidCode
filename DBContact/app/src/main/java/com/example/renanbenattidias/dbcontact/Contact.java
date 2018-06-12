package com.example.renanbenattidias.dbcontact;

import java.io.Serializable;

/**
 * Created by renanbenattidias on 04/04/18.
 */

public class Contact implements Serializable {
    private int id;
    private String name;
    private Integer age;
    private String address;
    private String phone;

    public Contact(int id, String name, Integer age, String address, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    public Contact(String name, Integer age, String address, String phone) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Integer age() {
        return age;
    }

    public String address() {
        return address;
    }

    public String phone() {
        return phone;
    }
}
