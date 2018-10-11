package com.capgemini.domain;

import javax.persistence.Entity;

@Entity
public class Guest extends Person {
    public Guest(){
        this.role = "ROLE_GUEST";
    }
}
