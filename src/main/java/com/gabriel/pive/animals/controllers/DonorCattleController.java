package com.gabriel.pive.animals.controllers;

import com.gabriel.pive.animals.dtos.DonorCattleDto;
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
@Tag(name = "Donor", description = "Donors management")
public class DonorCattleController {

    @Autowired
    private DonorCattleService donorCattleService;

    @Operation(summary = "Save a new donor", description = "It saves and returns a json with the new donor")
    @PostMapping
    public ResponseEntity<?> saveDonor(@Valid @RequestBody DonorCattleDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(donorCattleService.create(dto));
    }

    @Operation(summary = "List all donors", description = "It returns a json list with all donors")
    @GetMapping
    public ResponseEntity<List<DonorCattleDto>> listDonors(){
        return ResponseEntity.status(HttpStatus.OK).body(donorCattleService.findAll());
    }

    @Operation(summary = "Get available donor in fiv", description = "It returns a list with all donor cattle that wasn't used in the specific fiv.")
    @GetMapping("/{fivId}/available")
    public ResponseEntity<List<DonorCattleDto>> getAvailableReceiversInFiv(@PathVariable Long fivId){
        return ResponseEntity.status(HttpStatus.OK).body(donorCattleService.getAvailableDonorsInFiv(fivId));
    }

    @Operation(summary = "List donors with highest oocytes collected", description = "It returns a json list with the donors filtered and the average")
    @GetMapping("/highest-average-oocytes")
    public ResponseEntity<List<DonorCattleDto>> getDonorsWithHighestOocytesCollected() {
        List<DonorCattleDto> donors = donorCattleService.getDonorsWithHighestOocytesCollected();
        return ResponseEntity.status(HttpStatus.OK).body(donors);
    }

    @Operation(summary = "List donors with highest embryo percentage", description = "It returns a json list with the donors filtered and the percentage")
    @GetMapping("/highest-average-embryo-percentage")
    public ResponseEntity<List<DonorCattleDto>> getDonorsWithHighestEmbryoPercentage() {
        List<DonorCattleDto> donors = donorCattleService.getDonorsWithHighestEmbryoPercentage();
        return ResponseEntity.status(HttpStatus.OK).body(donors);
    }

    @Operation(summary = "Find donors with registration number", description = "It returns a json list with donor cattles that match the registration number")
    @GetMapping("/search")
    public ResponseEntity<List<DonorCattleDto>> findByRegistrationNumber(@RequestParam String registrationNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(donorCattleService.findByRegistrationNumber(registrationNumber));
    }

    @Operation(summary = "Find a donor by Id", description = "It returns a json with the donor found by Id ")
    @GetMapping("/{id}")
    public ResponseEntity<DonorCattleDto> getDonorById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(donorCattleService.findById(id));
    }

    @Operation(summary = "Delete a donor by Id", description = "It deletes the donor")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDonor(@PathVariable Long id){
        donorCattleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Edit a donor by Id", description = "It edits the donor's data")
    @PutMapping("/{id}")
    public ResponseEntity<DonorCattleDto> editDonor(@PathVariable Long id, @Valid @RequestBody DonorCattleDto dto){

        return ResponseEntity.status(HttpStatus.OK).body(donorCattleService.edit(id,dto));
    }
}
