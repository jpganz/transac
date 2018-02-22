package com.juan.demo.service;

import com.hazelcast.core.HazelcastInstance;
import com.juan.demo.model.entity.Transac;
import com.juan.demo.repository.TransacRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransacServiceTest {

    private static final long EPOCH_WITH_MILLIS = 1519323361L;
    private static final double AMOUNT = 123.45;
    private static final String HAZZEL_CAST_CACHE_MAP_NAME = "transacs";

    private TransacService transacService;

    @Mock
    private TransacRepository transacRepository;

    @Mock
    private HazelcastInstance hazelcastInstance;

    @Before
    public void setUp() {
        transacService = new TransacServiceImpl(transacRepository, hazelcastInstance);
    }

    @Test
    public void testSaveANewTransactionWithPastDate() {
       final Transac transaction = new Transac(new Date(), AMOUNT);
        when(transacRepository.save(any(Transac.class))).thenReturn(transaction);
        final Transac savedTransac = transacService.save(transaction);
        verify(transacRepository, times(1)).save(any(Transac.class));
        Assert.assertThat(savedTransac.getValue(), is(AMOUNT));
    }

    @Test
    public void testSaveANewTransactionWithPresentDate() {
        final Transac transaction = new Transac(new Date(), AMOUNT);
        when(transacRepository.save(any(Transac.class))).thenReturn(transaction);
        final Transac savedTransac = transacService.saveAndCachTransaction(transaction, EPOCH_WITH_MILLIS);
        verify(transacRepository, times(1)).save(any(Transac.class));
        verify(hazelcastInstance, times(1)).getMap(HAZZEL_CAST_CACHE_MAP_NAME);
        Assert.assertThat(savedTransac.getValue(), is(AMOUNT));
    }
}
