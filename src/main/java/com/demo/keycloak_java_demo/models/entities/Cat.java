package com.demo.keycloak_java_demo.models.entities;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "cat")
@Table(name = "cat")
@Data
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long catId;

    @Column(name = "name")
    String name;

    @Column(name = "age")
    int age;

    @Column(name = "ownerId")
    String ownerId;

}
