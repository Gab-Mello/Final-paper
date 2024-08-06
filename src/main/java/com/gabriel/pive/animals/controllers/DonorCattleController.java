package com.gabriel.pive.animals.controllers;

import com.gabriel.pive.animals.dtos.DonorCattleDto;
import com.gabriel.pive.animals.dtos.ReceiverCattleDto;
import com.gabriel.pive.animals.services.DonorCattleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("donor")
@Tag(name = "Donor", description = "donors management")
public class DonorCattleController {

    @Autowired
    private DonorCattleService donorCattleService;

    @Operation(summary = "Save a new donor", description = "It saves and returns a json with the new donor")
    @PostMapping
    public ResponseEntity<DonorCattleDto> saveDonor(@Valid @RequestBody DonorCattleDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(donorCattleService.create(dto));
    }

    @Operation(summary = "List all donors", description = "It returns a json list with all donors")
    @GetMapping
    public ResponseEntity<List<DonorCattleDto>> listDonors(){
        return ResponseEntity.status(HttpStatus.OK).body(donorCattleService.findAll());
    }

    @Operation(summary = "Find donor by registration number", description = "It returns a json list of donor cattle that match the registration number")
    @GetMapping("/search")
    public ResponseEntity<List<DonorCattleDto>> findByRegistrationNumber(@RequestParam String registrationNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(donorCattleService.findByRegistrationNumber(registrationNumber));
    }

    @Operation(summary = "Find a donor by Id", description = "It returns a json with the donor found by Id ")
    @GetMapping("/{id}")
    public ResponseEntity<DonorCattleDto> getDonorById(@PathVariable Long id){
        if (donorCattleService.findById(id) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(donorCattleService.findById(id));
    }

    @Operation(summary = "Delete a donor by Id", description = "It deletes the donor")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDonor(@PathVariable Long id){
        if (donorCattleService.findById(id) == null){
            return ResponseEntity.notFound().build();
        }
        donorCattleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Edit a donor by Id", description = "It edits the donor's data")
    @PutMapping("/{id}")
    public ResponseEntity<DonorCattleDto> editDonor(@PathVariable Long id, @RequestBody DonorCattleDto dto){
        if (donorCattleService.findById(id) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(donorCattleService.edit(id,dto));
    }
}
