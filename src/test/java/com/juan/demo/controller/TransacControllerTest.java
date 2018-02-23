package com.juan.demo.controller;

import com.juan.demo.model.TransacModel;
import com.juan.demo.model.entity.Transac;
import com.juan.demo.service.TransacService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransacControllerTest {

    private static final long EPOCH_WITH_MILLIS = 1519323361L;
    private static final double AMOUNT = 123.45;
    @Mock
    private TransacService transacService;

    private TransacController transacController;


    @Before
    public void setUp() {
        transacController = new TransacController(transacService);
    }

    @Test
    public void testSaveANewTransactionWithPastDate() {
        final TransacModel transacModel = new TransacModel(EPOCH_WITH_MILLIS, AMOUNT);
        final Instant instantTime = Instant.ofEpochMilli(EPOCH_WITH_MILLIS);
        final Transac savedTransac = new Transac(Date.from(instantTime), AMOUNT);
        when(transacService.save(new Transac(Date.from(instantTime), transacModel.getValue()))).thenReturn(savedTransac);
        final ResponseEntity responseEntity = transacController.saveANewTransaction(transacModel);
        verify(transacService, times(1)).save(any(Transac.class));
        verify(transacService, never()).saveAndCachTransaction(any(Transac.class), any(Long.class));
        Assert.assertThat(responseEntity.getStatusCode().value(), is(204));
    }

    @Test
    public void testSaveANewTransactionWithPastDateThrowingException() {
        final TransacModel transacModel = new TransacModel(EPOCH_WITH_MILLIS, AMOUNT);
        when(transacService.save(any(Transac.class))).thenThrow(new RuntimeException());
        final ResponseEntity responseEntity = transacController.saveANewTransaction(transacModel);
        verify(transacService, times(1)).save(any(Transac.class));
        verify(transacService, never()).saveAndCachTransaction(any(Transac.class), any(Long.class));
        Assert.assertThat(responseEntity.getStatusCode().value(), is(500));
    }

    @Test
    public void testGetTransactions() {
        final List<Double> result = new ArrayList<>();
        when(transacService.getTransactionValuesFromLastMinute()).thenReturn(result);
        transacController.getTransactions();
        verify(transacService, times(1)).getTransactionValuesFromLastMinute();

    }
}
