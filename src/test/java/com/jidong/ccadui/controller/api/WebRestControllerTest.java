package com.jidong.ccadui.controller.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class WebRestControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void Profile() {
        //when
        String profile = this.testRestTemplate.getForObject("/profile", String.class);

        //then
//        assertEquals("local", profile);
    }

    @Test
    public void DB연결확인() {

    }
}