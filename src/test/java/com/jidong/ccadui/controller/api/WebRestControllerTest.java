package com.jidong.ccadui.controller.api;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class WebRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Profile("local")
    public void Profile() {
        //when
        String profile = this.restTemplate.getForObject("http://localhost:8080/profile", String.class);

        //then
        assertEquals("local", profile);
    }

    @Test
    public void DB연결확인() {

    }
}