package com.hua.limitsservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-service")
@Data
public class Configuration {

    private int minimum;
    private int maximum;
    private String home;
    private String userhome;
}
