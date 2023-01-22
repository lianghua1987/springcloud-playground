package com.hua.currencyexchangeservice.controller;

import com.hua.currencyexchangeservice.bean.CurrencyExchange;
import com.hua.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        String port = environment.getProperty("local.server.port");
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from.toUpperCase(), to.toUpperCase());
        currencyExchange.setEnvironment(port);

        if(currencyExchange == null){
            throw new RuntimeException("Invalid");
        }

        return currencyExchange;
    }
}
