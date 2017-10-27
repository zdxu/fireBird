package com.zdxu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by zhaodexu on 2017/8/29.
 */
@SpringBootApplication
public class ShopWebStartUp extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ShopWebStartUp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ShopWebStartUp.class, args);
    }
}
