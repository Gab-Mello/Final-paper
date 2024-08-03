package com.gabriel.pive.controllers;

import com.gabriel.pive.dtos.BullDto;
import com.gabriel.pive.services.BullService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bull")
@Tag(name = "Bull", description = "bulls management")
public class BullController {

    @Autowired
    private BullService bullService;

    @Operation(summary = "Save a new receiver cattle", description = "It saves and returns a json with the new receiver cattle")
    @PostMapping
    public ResponseEntity<BullDto> saveBull(@Valid @RequestBody BullDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bullService.create(dto));
    }

    @Operation(summary = "List all receiver cattles", description = "It returns a list of json with all receiver cattles")
    @GetMapping
    public ResponseEntity<List<BullDto>> listBulls(){
        return ResponseEntity.status(HttpStatus.OK).body(bullService.findAll());
    }

    @Operation(summary = "Find a receiver cattle by Id", description = "It returns a json with the receiver cattle finded by Id ")
    @GetMapping("/{id}")
    public ResponseEntity<BullDto> getBullById(@PathVariable Long id){
        if (bullService.findById(id)==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(bullService.findById(id));
    }

    @Operation(summary = "Delete a receiver cattle by Id", description = "It deletes the receiver cattle")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBull(@PathVariable Long id){
        if (bullService.findById(id)==null){
            return ResponseEntity.notFound().build();
        }
        bullService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Edit a receiver cattle by Id", description = "It edit the receiver cattle")
    @PutMapping("/{id}")
    public ResponseEntity<BullDto> editBull(@PathVariable Long id, @RequestBody BullDto dto){
        if (bullService.findById(id)==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(bullService.edit(id,dto));
    }

}
