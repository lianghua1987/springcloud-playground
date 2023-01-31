package com.hua.currencyexchangeservice.controller;

import com.hua.currencyexchangeservice.bean.CurrencyExchange;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "server.port=8889")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(PER_CLASS)
public class ITCurrencyExchangeControllerTest {

    @Value("${server.port}")
    int port;

    @LocalServerPort
    int randomPort;

    @Autowired
    TestRestTemplate template;

    int value;

    @Test
    @Order(2)
    public void test1() {
        System.out.println("predefined:" + port);
        System.out.println("random:" + randomPort);
        ResponseEntity<CurrencyExchange> entity = template.getForEntity("/currency-exchange/from/usd/to/cny", CurrencyExchange.class);
        System.out.println(entity.getBody().toString());
    }

    @Test
    @Order(3)
    public void test3() {
        System.out.println(value);
    }

    @Test
    @Order(1)
    public void test2() {
        value = 2;
        System.out.println(value);
    }


}
