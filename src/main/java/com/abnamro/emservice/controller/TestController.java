package com.abnamro.emservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class TestController {

    @GetMapping("/api/test")
    public String testEndpoint() {
        return "Test endpoint works!";
    }

}

