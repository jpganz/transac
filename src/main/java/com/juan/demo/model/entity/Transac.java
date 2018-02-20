package com.juan.demo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "animal")
public class Transac {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;

}
