package com.gabriel.pive.fiv.controllers;

import com.gabriel.pive.fiv.dtos.FivResponseDto;
import com.gabriel.pive.fiv.exceptions.FivNotFoundException;
import com.gabriel.pive.fiv.services.FivService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "List all fivs", description = "It returns a json list with all fivs")
    @GetMapping
    public ResponseEntity<List<FivResponseDto>> listFives(){
        return ResponseEntity.status(HttpStatus.OK).body(fivService.getAllFives());
    }

    @Operation(summary = "Get a fiv by Id", description = "It returns a json with the fiv")
    @GetMapping("/{id}")
    public ResponseEntity<?> getFivById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(fivService.getFivById(id));
        }
        catch (FivNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "List all in process fives", description = "It returns a json list with all in process fives")
    @GetMapping("/in-process")
    public ResponseEntity<List<FivResponseDto>> listInProcessFives(){
        return ResponseEntity.status(HttpStatus.OK).body(fivService.getInProcessFives());
    }

    @Operation(summary = "List all oocyte collection completed fives",
            description = "It returns a json list with all oocyte collection completed fives")
    @GetMapping("/oocyteCollection-completed")
    public ResponseEntity<List<FivResponseDto>> listOocyteCollectionCompletedFives(){
        return ResponseEntity.status(HttpStatus.OK).body(fivService.getOocyteCollectionCompletedFives());
    }

    @Operation(summary = "List all completed fives",
            description = "completed fives are those that all embryos are registered")
    @GetMapping("/completed")
    public ResponseEntity<List<FivResponseDto>> listCompletedFives(){
        return ResponseEntity.status(HttpStatus.OK).body(fivService.getCompletedFives());
    }

}
