package com.hua.currencyexchangeservice.repository;

import com.hua.currencyexchangeservice.bean.CurrencyExchange;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CurrencyExchangeRepositoryTest {

    @Autowired
    TestEntityManager manager;

    @Autowired
    CurrencyExchangeRepository repository;

    @Test
    public void test1() {
        CurrencyExchange currencyExchange = manager.find(CurrencyExchange.class, 10002l);
        System.out.println(currencyExchange.toString());
    }


    @Test
    public void test2() {
        manager.persistAndFlush(new CurrencyExchange(88888l, "AAA", "BBB", BigDecimal.TEN, "hell"));
        CurrencyExchange currencyExchange = repository.findByFromAndTo("AAA", "BBB");
        assertEquals(BigDecimal.TEN, currencyExchange.getConversionMultiple());
    }
}
