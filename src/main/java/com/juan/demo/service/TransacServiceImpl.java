package com.juan.demo.service;

import com.juan.demo.model.Transac;
import com.juan.demo.repository.TransacRepository;

import java.util.List;

public class TransacServiceImpl implements TransacService {

    private TransacRepository transacRepository;

    public TransacServiceImpl(final TransacRepository transacRepository) {
        this.transacRepository = transacRepository;
    }

    @Override
    public List<Transac> getTransacs(){
        return transacRepository.findAll();
    }
}
