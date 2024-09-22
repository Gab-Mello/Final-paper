package com.gabriel.pive.animals.controllers;

import com.gabriel.pive.animals.dtos.BullAverageEmbryoDto;
import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.animals.services.BullService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bull")
@Tag(name = "Bull", description = "bulls management")
public class BullController {

    @Autowired
    private BullService bullService;

    @Operation(summary = "Save a new bull", description = "It saves and returns a json with the new bull")
    @PostMapping
    public ResponseEntity<?> saveBull(@Valid @RequestBody BullDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bullService.create(dto));
    }

    @Operation(summary = "List all bulls", description = "It returns a json list with all bulls")
    @GetMapping
    public ResponseEntity<List<BullDto>> listBulls(){
        return ResponseEntity.status(HttpStatus.OK).body(bullService.findAll());
    }

    @Operation(summary = "List bulls with highest embryo percentage", description = "It returns a json list with the bulls filtered and the percentage")
    @GetMapping("/highest-average-embryo-percentage")
    public ResponseEntity<List<BullDto>> getBullsWithHighestEmbryoPercentage() {
        List<BullDto> bulls = bullService.getBullsWithHighestEmbryoPercentage();
        return ResponseEntity.status(HttpStatus.OK).body(bulls);
    }

    @Operation(summary = "Find bulls with registration number", description = "It returns a json list of bulls that match the registration number")
    @GetMapping("/search")
    public ResponseEntity<List<BullDto>> findByRegistrationNumber(@RequestParam String registrationNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(bullService.findByRegistrationNumber(registrationNumber));
    }

    @Operation(summary = "Find a bull by Id", description = "It returns a json with the bull found by Id ")
    @GetMapping("/{id}")
    public ResponseEntity<BullDto> getBullById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bullService.findById(id));
    }

    @Operation(summary = "Delete a bull by Id", description = "It deletes the bull")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBull(@PathVariable Long id){
        bullService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Edit a bull by Id", description = "It edits the bull's data")
    @PutMapping("/{id}")
    public ResponseEntity<BullDto> editBull(@PathVariable Long id, @Valid @RequestBody BullDto dto){
        if (bullService.findById(id)==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(bullService.edit(id,dto));
    }

}
