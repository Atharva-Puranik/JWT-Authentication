package com.atharvapuranik.imapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/secured")
public class securedController {

    @GetMapping("/page")
    public ResponseEntity<String> securedEndpoint(){
        return ResponseEntity.ok("This is a secured Endpoint");
    }
}
