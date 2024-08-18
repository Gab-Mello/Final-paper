package com.gabriel.pive.fiv.controllers;

import com.gabriel.pive.fiv.dtos.FivResponseDto;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.services.FivService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fiv")
@Tag(name = "Fiv", description = "Fiv management")
public class FivController {

    @Autowired
    private FivService fivService;

    @Operation(summary = "Create a new fiv", description = "It saves and returns a json with the new fiv")
    @PostMapping
    public ResponseEntity<FivResponseDto> createFiv(){

    return ResponseEntity.status(HttpStatus.CREATED).body(fivService.createFiv());
    }
}
