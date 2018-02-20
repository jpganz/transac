package com.juan.demo.controller;

import com.codahale.metrics.annotation.Timed;
import com.juan.demo.model.entity.Transac;
import com.juan.demo.service.TransacService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Api("Animals")
@RestController
@RequestMapping(value = "/animals")
public class TransacController {

    private final TransacService transacService;

    public TransacController(final TransacService transacService) {
        this.transacService = transacService;
    }

    @Timed
    @ApiOperation(value = "Get current animals")
    @ResponseBody
    @RequestMapping(method = GET, produces = APPLICATION_JSON_UTF8_VALUE)
    public List<Transac> getAnimals(){
        return transacService.getAnimalNames();
    }



}
