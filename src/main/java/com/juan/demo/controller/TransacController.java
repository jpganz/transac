package com.juan.demo.controller;

import com.codahale.metrics.annotation.Timed;
import com.juan.demo.model.TransacModel;
import com.juan.demo.model.entity.Transac;
import com.juan.demo.service.TransacService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Api("Transactions")
@RestController
@RequestMapping(value = "/transaction")
public class TransacController {

    private final TransacService transacService;

    public TransacController(final TransacService transacService) {
        this.transacService = transacService;
    }

    @Timed
    @ApiOperation(value = "Get transaction values for the last 60 seconds")
    @ResponseBody
    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Double>> getTransactions() {
        return new ResponseEntity<>(transacService.getTransactionValuesFromLastMinute(), null, OK);
    }

    @Timed
    @ApiOperation(value = "Save a new transaction")
    @ResponseBody
    @PostMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity saveANewTransaction(@RequestBody final TransacModel transac) {
        try {
            HttpStatus returnValue = CREATED;
            final Instant instantOfThisTransaction = convertToInstant(transac.getTime());
            if (isDataCacheable(instantOfThisTransaction)) {
                //todo: seems like Instant is (sadly) not supported
                transacService.saveAndCachTransaction(new Transac(Date.from(instantOfThisTransaction), transac.getValue()), transac.getTime());
            } else {
                returnValue = NO_CONTENT;
                transacService.save(new Transac(Date.from(instantOfThisTransaction), transac.getValue()));
            }
            return new ResponseEntity<>(transac, null, returnValue);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving new transaction ", null, INTERNAL_SERVER_ERROR);
        }
    }

    private Instant convertToInstant(final long epochTime) {
        return Instant.ofEpochMilli(epochTime);
    }

    private boolean isDataCacheable(final Instant instantOfTransac) {
        if (instantOfTransac.isAfter(Instant.now().minusSeconds(60))) {
            return true;
        }
        return false;
    }

}
