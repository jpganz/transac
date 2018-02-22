package com.juan.demo.service;

import com.hazelcast.core.HazelcastInstance;
import com.juan.demo.model.entity.Transac;
import com.juan.demo.repository.TransacRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;

public class TransacServiceImpl implements TransacService {

    private static final String HAZZEL_CAST_CACHE_MAP_NAME = "transacs";

    private TransacRepository transacRepository;
    private HazelcastInstance hazelcastInstance;

    public TransacServiceImpl(final TransacRepository transacRepository, final HazelcastInstance hazelcastInstance) {
        this.transacRepository = transacRepository;
        this.hazelcastInstance = hazelcastInstance;
    }

    @Override
    public List<Double> getTransactionValuesFromLastMinute() {
        final List<Double> transactionsValues = new ArrayList<>();
        final ConcurrentMap<Long, Double> map = hazelcastInstance.getMap(HAZZEL_CAST_CACHE_MAP_NAME);
        map.forEach((k, v) -> transactionsValues.add(v));
        return transactionsValues;
    }

    @Override
    public Transac save(final Transac transac) {
        transacRepository.save(transac);
        return transac;
    }

    @Override
    @Transactional
    public Transac saveAndCachTransaction(final Transac transac, final long cacheKey) {
        this.save(transac);
        setOnCache(transac, cacheKey);
        return transac;
    }


    private void setOnCache(final Transac transac, final long cacheKey) {
        final Random random = new Random();
        final long safeCacheKey = cacheKey - random.nextLong();
        try {
            hazelcastInstance.getMap(HAZZEL_CAST_CACHE_MAP_NAME).put(safeCacheKey, transac.getValue());
        } catch (Exception e) {
            //todo log the error xD
        }
    }


}
