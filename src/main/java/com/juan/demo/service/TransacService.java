package com.juan.demo.service;

import com.juan.demo.model.TransacModel;
import com.juan.demo.model.entity.Transac;

import java.util.List;

public interface TransacService {

    List<Double> getTransactionValuesFromLastMinute();

    Transac save(Transac transac);

    Transac saveAndCachTransaction(Transac transac, long cacheKey);

}
