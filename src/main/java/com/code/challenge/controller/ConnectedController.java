package com.code.challenge.controller;

import com.code.challenge.service.ConnectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "connected")
public class ConnectedController {

    private ConnectedService service;

    @Autowired
    public ConnectedController(ConnectedService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Boolean> isConnected(@RequestParam(value = "origin") String firstCity,
                                                   @RequestParam(value = "destination") String secondCity) {
        return new ResponseEntity(service.isConnected(firstCity, secondCity), HttpStatus.OK);
    }


}
