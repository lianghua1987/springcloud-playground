package com.hua.currencyexchangeservice.controller;

import com.hua.currencyexchangeservice.bean.CurrencyExchange;
import com.hua.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

        logger.info("Endpoint retrieveExchangeValue gets called from {} to {}", from, to);
        // problem with mock environment, based on github, should consider using
        // @TestPropertySource(properties="spring.profiles.active=local") if needed
        String port = "8000"; //environment.getProperty("local.server.port");
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from.toUpperCase(), to.toUpperCase());
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}
