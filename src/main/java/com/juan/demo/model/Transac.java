package com.juan.demo.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;

public class Transac {

    private long id;
    private String name;

    public Transac(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    @ApiModelProperty(value = "ID")
    public long getId() {
        return id;
    }


}
