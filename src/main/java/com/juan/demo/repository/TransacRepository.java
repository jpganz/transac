package com.juan.demo.repository;

import com.juan.demo.model.Transac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacRepository extends JpaRepository<Transac, Long> {

    List<Transac> findAll();
}
