package com.gabriel.pive.fiv.cultivation.controllers;

import com.gabriel.pive.fiv.cultivation.dtos.CultivationRequestDto;
import com.gabriel.pive.fiv.cultivation.dtos.CultivationResponseDto;
import com.gabriel.pive.fiv.cultivation.services.CultivationService;
import com.gabriel.pive.fiv.oocyteCollection.dtos.OocyteCollectionRequestDto;
import com.gabriel.pive.fiv.oocyteCollection.dtos.OocyteCollectionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Cultivation", description = "Cultivation management")
@RestController
@RequestMapping("/cultivation")
public class CultivationController {

    @Autowired
    private CultivationService cultivationService;

    @Operation(summary = "Save a new cultivation", description = "It saves and returns a json with the new cultivation")
    @PostMapping
    public ResponseEntity<CultivationResponseDto> newCultivation(@RequestBody CultivationRequestDto dto){
        try{
            CultivationResponseDto oocyteCollection = cultivationService.newCultivation(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(oocyteCollection);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
