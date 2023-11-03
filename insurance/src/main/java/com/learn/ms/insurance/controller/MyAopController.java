package com.learn.ms.insurance.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyAopController {

	@GetMapping("hello")
    public ResponseEntity<?> getHello(
            @RequestParam("name") String username
    ) {
        return ResponseEntity.ok("Hello, " + username);
    }

}