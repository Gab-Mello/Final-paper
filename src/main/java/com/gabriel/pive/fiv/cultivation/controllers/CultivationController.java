package com.gabriel.pive.fiv.cultivation.controllers;

import com.gabriel.pive.fiv.cultivation.dtos.CultivationRequestDto;
import com.gabriel.pive.fiv.cultivation.dtos.CultivationResponseDto;
import com.gabriel.pive.fiv.cultivation.exceptions.CultivationNotFoundException;
import com.gabriel.pive.fiv.cultivation.exceptions.FivAlreadyHasCultivation;
import com.gabriel.pive.fiv.cultivation.services.CultivationService;
import com.gabriel.pive.fiv.exceptions.FivNotFoundException;
import com.gabriel.pive.fiv.oocyteCollection.dtos.OocyteCollectionRequestDto;
import com.gabriel.pive.fiv.oocyteCollection.dtos.OocyteCollectionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cultivation", description = "Cultivation management")
@RestController
@RequestMapping("/cultivation")
public class CultivationController {

    @Autowired
    private CultivationService cultivationService;

    @Operation(summary = "Save a new cultivation", description = "It saves and returns a json with the new cultivation")
    @PostMapping
    public ResponseEntity<?> newCultivation(@RequestBody CultivationRequestDto dto){
        try{
            CultivationResponseDto cultivation = cultivationService.newCultivation(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(cultivation);
        }
        catch (FivNotFoundException | FivAlreadyHasCultivation e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @Operation(summary = "Get all cultivations", description = "It returns a json list with all cultivations")
    @GetMapping
    public ResponseEntity<List<CultivationResponseDto>> getAllCultivations(){
        return ResponseEntity.status(HttpStatus.OK).body(cultivationService.getAllCultivations());
    }

    @Operation(summary = "Get a cultivation by Id", description = "It returns a json with the cultivation")
    @GetMapping("/{id}")
    public ResponseEntity<?> getCultivationById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cultivationService.getCultivationById(id));
        }
        catch (CultivationNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
