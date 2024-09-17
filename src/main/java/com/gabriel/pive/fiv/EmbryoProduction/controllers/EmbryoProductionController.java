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

@Tag(name = "Production", description = "Production management")
@RestController
@RequestMapping("/production")
public class EmbryoProductionController {

    @Autowired
    private ProductionService productionService;

    @Operation(summary = "Save a new embryo production", description = "It saves and returns a json with the new production")
    @PostMapping
    public ResponseEntity<?> newProduction(@Valid @RequestBody ProductionRequestDto dto){
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
    public ResponseEntity<?> getProductionById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(productionService.getProductionById(id));
    }

//    @Operation(summary = "edit a cultivation by Id", description = "It returns a json with the cultivation edited")
//    @PutMapping("/{id}")
//    public ResponseEntity<?> editCultivation(@PathVariable Long id, @Valid @RequestBody ProductionRequestDto dto){
//        CultivationResponseDto cultivation = productionService.editCultivation(id,dto);
//        return ResponseEntity.status(HttpStatus.OK).body(cultivation);
//    }
}
