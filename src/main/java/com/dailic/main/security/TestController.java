package com.dailic.main.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/public/hello")
    public String publicEndpoint() {
        return "Public: no token needed";
    }

    @GetMapping("/private/hello")
    public String privateEndpoint() {
        return "Private: token required";
    }


}