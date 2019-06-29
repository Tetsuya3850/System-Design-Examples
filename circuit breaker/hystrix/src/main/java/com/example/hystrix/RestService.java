package com.example.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplate rest) {
        this.restTemplate = rest;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public String get() {
        URI uri = URI.create("http://localhost:8090");

        return this.restTemplate.getForObject(uri, String.class);
    }

    public String reliable() {
        return "Fallback response";
    }

}
