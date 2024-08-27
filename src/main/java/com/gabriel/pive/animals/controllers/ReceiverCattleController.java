package com.gabriel.pive.animals.controllers;

import com.gabriel.pive.animals.dtos.ReceiverCattleDto;
import com.gabriel.pive.animals.exceptions.RegistrationNumberAlreadyExistsException;
import com.gabriel.pive.animals.services.ReceiverCattleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("receiver")
@Tag(name = "Receiver", description = "receiver cattles management")
public class ReceiverCattleController {

    @Autowired
    private ReceiverCattleService receiverCattleService;

    @Operation(summary = "Save a new receiver cattle", description = "It saves and returns a json with the new receiver cattle")
    @PostMapping
    public ResponseEntity<?> saveReceiver(@Valid @RequestBody ReceiverCattleDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(receiverCattleService.create(dto));
    }

    @Operation(summary = "List all receiver cattles", description = "It returns a json list with all receiver cattles")
    @GetMapping
    public ResponseEntity<List<ReceiverCattleDto>> listReceivers(){
        return ResponseEntity.status(HttpStatus.OK).body(receiverCattleService.findAll());
    }

    @Operation(summary = "Find receiver by registration number", description = "It returns a json with the receiver cattle with the specific registration number")
    @GetMapping("/search")
    public ResponseEntity<List<ReceiverCattleDto>> findByRegistrationNumber(@RequestParam String registrationNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(receiverCattleService.findByRegistrationNumber(registrationNumber));
    }

    @Operation(summary = "Find a receiver cattle by Id", description = "It returns a json with the receiver cattle found by Id ")
    @GetMapping("/{id}")
    public ResponseEntity<ReceiverCattleDto> getReceiverCattleById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(receiverCattleService.findById(id));
    }

    @Operation(summary = "Delete a receiver cattle by Id", description = "It deletes the receiver cattle")
    @DeleteMapping("/{id}")
    public ResponseEntity<ReceiverCattleDto> deleteReceiverCattle(@PathVariable Long id){
        receiverCattleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Edit a receiver cattle by Id", description = "It edits the receiver cattle's data")
    @PutMapping("/{id}")
    public ResponseEntity<ReceiverCattleDto> editReceiverCattle(@PathVariable Long id, @RequestBody ReceiverCattleDto dto){
        if (receiverCattleService.findById(id)==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(receiverCattleService.edit(id,dto));
    }

    @Operation(summary = "get all available receiver cattle", description = "It returns a list with all receiver cattle that doesn't have an embryo")
    @GetMapping("/available")
    public ResponseEntity<List<ReceiverCattleDto>> getAvailableReceivers(){
        return ResponseEntity.status(HttpStatus.OK).body(receiverCattleService.getAvailableReceivers());
    }



}
