package com.juan.demo.model.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "transac")
@Repository
public class Transac {

    public Transac(){ //required due odd behaviours of jpa -- reflexion

    }

    public Transac(final Instant time, final Double value) {
        this.time = time;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(nullable = false)
    private Instant time;


    @Column(nullable = false)
    private Double value;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(final Instant time) {
        this.time = time;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(final Double value) {
        this.value = value;
    }
}
