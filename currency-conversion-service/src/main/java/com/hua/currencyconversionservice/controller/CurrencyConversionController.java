package com.hua.currencyconversionservice.controller;

import com.hua.currencyconversionservice.bean.CurrencyConversion;
import com.hua.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class CurrencyConversionController {
    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeProxy proxy;

    @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        //String port = environment.getProperty("local.server.port");

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate()
                .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
        CurrencyConversion conversion = responseEntity.getBody();
        if (conversion == null) throw new RuntimeException("CurrencyConversion should not be null");
        return new CurrencyConversion(conversion.getId(), from, to, quantity, conversion.getConversionMultiple(), quantity.multiply(conversion.getConversionMultiple()), conversion.getEnvironment());
    }

    @GetMapping("currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        //String port = environment.getProperty("local.server.port");

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        CurrencyConversion conversion = proxy.retrieveExchangeValue(from, to);

        if (conversion == null) throw new RuntimeException("CurrencyConversion should not be null");

        return new CurrencyConversion(conversion.getId(), from, to, quantity, conversion.getConversionMultiple(), quantity.multiply(conversion.getConversionMultiple()), conversion.getEnvironment());
    }
}
