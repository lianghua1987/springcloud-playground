package com.hua.limitsservice.controller;

import com.hua.limitsservice.bean.Limits;
import com.hua.limitsservice.configuration.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    private Configuration configuration;

    public LimitsController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/limits")
    public Limits retriveLimits() {
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
