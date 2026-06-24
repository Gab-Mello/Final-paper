package com.gabriel.pive.fiv.embryoproduction.controllers;

import com.gabriel.pive.fiv.embryoproduction.dtos.ProductionResponseDto;
import com.gabriel.pive.fiv.embryoproduction.dtos.ProductionRequestDto;
import com.gabriel.pive.fiv.embryoproduction.services.ProductionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Production", description = "Production management")
@RestController
@RequestMapping("/production")
@RequiredArgsConstructor
public class EmbryoProductionController {

    private final ProductionService productionService;

    @Operation(summary = "Save a new embryo production", description = "It saves and returns a json with the new production")
    @PostMapping
    public ResponseEntity<ProductionResponseDto> newProduction(@Valid @RequestBody ProductionRequestDto dto){
            ProductionResponseDto production = productionService.newProduction(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(production);
    }

    @Operation(summary = "Get all embryo productions", description = "It returns a json list with all productions")
    @GetMapping
    public ResponseEntity<List<ProductionResponseDto>> getAllProductions(){
        return ResponseEntity.status(HttpStatus.OK).body(productionService.getAllProductions());
    }

    @Operation(summary = "Get a embryo production by Id", description = "It returns a json with the production")
    @GetMapping("/{id}")
    public ResponseEntity<ProductionResponseDto> getProductionById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(productionService.getProductionById(id));
    }
}
