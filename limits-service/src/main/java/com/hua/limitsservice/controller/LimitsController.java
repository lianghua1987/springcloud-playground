package com.hua.limitsservice.controller;

import com.hua.limitsservice.bean.Limits;
import com.hua.limitsservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Configuration conf;

    @GetMapping("/limits")
    public Limits retriveLimits() {
        return new Limits(conf.getMinimum(), conf.getMaximum());
    }
}
