package com.juan.demo.model.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.Instant;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "transac")
@Repository
public class Transac {

    public Transac(){ //required due odd behaviours of jpa -- reflexion

    }

    public Transac(final Date timeField, final Double value) {
        this.timeField = timeField;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(name = "timefield", nullable = false)
    private Date timeField;


    @Column(nullable = false)
    private Double value;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(final Double value) {
        this.value = value;
    }

    public Date getTimeField() {
        return timeField;
    }

    public void setTimeField(final Date timeField) {
        this.timeField = timeField;
    }
}
