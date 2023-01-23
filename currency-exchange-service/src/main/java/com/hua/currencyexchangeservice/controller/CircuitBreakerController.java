package com.hua.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/test-endpoint-retry")
    @Retry(name = "test-endpoint-retry", fallbackMethod = "testEndpointFallbackRetry")
    public String testEndpointRetry() {
        logger.info("Start test endpoint retry call");
        ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8080/not-exist", String.class);
        return entity.getBody();
    }

    @GetMapping("/test-endpoint-circuitbreaker")
    @CircuitBreaker(name = "default", fallbackMethod = "testEndpointFallbackCircuitBreaker")
    public String testEndpointCircuitBreaker() {
        logger.info("Start test endpoint circuit breaker call");
        ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8080/not-exist", String.class);
        return entity.getBody();
    }

    @GetMapping("/test-endpoint-ratelimiter")
    @CircuitBreaker(name = "default")
    @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public String testEndpointRateLimiter() {
        return "test endpoint rate limiter 2reqs/10s";
    }

    private String testEndpointFallbackCircuitBreaker(Exception e) {
        return "This is test endpoint fallback circuit breaker response -> " + e.getMessage();
    }

    private String testEndpointFallbackRetry(Exception e) {
        return "This is test endpoint fallback retry response -> " + e.getMessage();
    }
}
