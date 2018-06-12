package com.example.renanbenattidias.dbclass;

import java.io.Serializable;

/**
 * Created by renanbenattidias on 27/03/18.
 */

public class Contact implements Serializable {

    private Integer id;

    private String email;
    private String password;
    private String name;

    public Contact(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Contact(String email, String password, String name, Integer id) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.id = id;
    }

    public String email() {
        return email;
    }

    public String name() {
        return name;
    }

    public String password() {
        return password;
    }

    public Integer id() {
        return id;
    }

}
