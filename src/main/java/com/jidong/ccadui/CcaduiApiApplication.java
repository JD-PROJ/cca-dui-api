package com.jidong.ccadui;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CcaduiApiApplication {
    public static final String APPLICATION_LOCATIONS = "spring.config.location="
                + "classpath:application.yml,"
                + "app/config/ccadui/application-real.yml"; // application-real.yml 위치

    public static void main(String[] args) {
        new SpringApplicationBuilder(CcaduiApiApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

}
