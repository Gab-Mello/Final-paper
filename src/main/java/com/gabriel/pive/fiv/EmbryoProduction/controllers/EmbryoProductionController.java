package com.gabriel.pive.fiv.EmbryoProduction.controllers;

import com.gabriel.pive.fiv.EmbryoProduction.dtos.ProductionResponseDto;
import com.gabriel.pive.fiv.EmbryoProduction.dtos.ProductionRequestDto;
import com.gabriel.pive.fiv.EmbryoProduction.services.ProductionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cultivation", description = "Cultivation management")
@RestController
@RequestMapping("/cultivation")
public class EmbryoProductionController {

    @Autowired
    private ProductionService productionService;

    @Operation(summary = "Save a new embryo production", description = "It saves and returns a json with the new production")
    @PostMapping
    public ResponseEntity<?> newProduction(@Valid @RequestBody ProductionRequestDto dto){
            ProductionResponseDto cultivation = productionService.newProduction(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(cultivation);
    }

    @Operation(summary = "Get all cultivations", description = "It returns a json list with all cultivations")
    @GetMapping
    public ResponseEntity<List<ProductionResponseDto>> getAllCultivations(){
        return ResponseEntity.status(HttpStatus.OK).body(productionService.getAllCultivations());
    }

    @Operation(summary = "Get a cultivation by Id", description = "It returns a json with the cultivation")
    @GetMapping("/{id}")
    public ResponseEntity<?> getCultivationById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(productionService.getCultivationById(id));
    }

//    @Operation(summary = "edit a cultivation by Id", description = "It returns a json with the cultivation edited")
//    @PutMapping("/{id}")
//    public ResponseEntity<?> editCultivation(@PathVariable Long id, @Valid @RequestBody ProductionRequestDto dto){
//        CultivationResponseDto cultivation = productionService.editCultivation(id,dto);
//        return ResponseEntity.status(HttpStatus.OK).body(cultivation);
//    }
}
