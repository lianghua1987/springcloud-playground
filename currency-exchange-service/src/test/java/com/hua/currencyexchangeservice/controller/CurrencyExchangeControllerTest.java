package com.hua.currencyexchangeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hua.currencyexchangeservice.bean.CurrencyExchange;
import com.hua.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = CurrencyExchangeController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
//@AutoConfigureMockMvc(addFilters = false) // exclude spring security features
public class CurrencyExchangeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CurrencyExchangeRepository repository;

//    Environment is not a bean, can't be injected
//    @MockBean
//    Environment environment;

    @Test
    public void test1() throws Exception {

        when(repository.findByFromAndTo(anyString(), anyString())).thenReturn(new CurrencyExchange(1l, "USD", "CNY", BigDecimal.TEN, "local"));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/currency-exchange/from/usd/to/cny")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        String resp = mvcResult.getResponse().getContentAsString();
        CurrencyExchange currencyExchange = new ObjectMapper().readValue(resp, CurrencyExchange.class);
        System.out.println(currencyExchange);
    }
}
