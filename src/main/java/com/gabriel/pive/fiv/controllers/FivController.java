package com.gabriel.pive.fiv.controllers;

import com.gabriel.pive.fiv.dtos.FivResponseDto;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.services.FivService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fiv")
public class FivController {

    @Autowired
    private FivService fivService;

    @PostMapping
    public ResponseEntity<FivResponseDto> createFiv(){

    return ResponseEntity.status(HttpStatus.CREATED).body(fivService.createFiv());
    }
}
