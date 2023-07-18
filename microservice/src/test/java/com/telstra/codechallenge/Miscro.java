package com.telstra.codechallenge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MicroserviceApplicationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testHealth() throws RestClientException, MalformedURLException {
        ResponseEntity<String> response = restTemplate
                .getForEntity(new URL("http://localhost:" + port + "/actuator/health")
                        .toString(), String.class);
        System.out.println("response: " + response);
        assertEquals("{\"status\":\"UP\"}", response
                .getBody());
    }

    @Test
    void testInfo() throws RestClientException, MalformedURLException {
        ResponseEntity<Object> response = restTemplate
                .getForEntity(new URL("http://localhost:" + port + "/actuator/info")
                        .toString(), Object.class);
        System.out.println("response: " + response);
//        assertEquals("{\"status\":\"UP\"}", response
//                .getBody());
    }

}
