package com.juan.demo.service;

import com.juan.demo.model.TransacModel;
import com.juan.demo.model.entity.Transac;

import java.util.List;

public interface TransacService {

    List<Double> getTransacsValues();

    Transac save(Transac transac);

    Transac saveAndCachTransac(Transac transac, long cacheKey);

}
