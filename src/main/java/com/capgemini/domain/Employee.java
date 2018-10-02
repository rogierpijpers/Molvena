package com.capgemini.domain;

import javax.persistence.Entity;

@Entity
public class Employee extends Person {
    public Employee(){
        this.setRole("ROLE_RECEPTIONIST");
    }
}
