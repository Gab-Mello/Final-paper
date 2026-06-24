package com.gabriel.pive.fiv.embryoproduction.controllers;

import com.gabriel.pive.fiv.embryoproduction.dtos.TransferInitialDto;
import com.gabriel.pive.fiv.embryoproduction.dtos.TransferResponseDto;
import com.gabriel.pive.fiv.embryoproduction.services.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Embryos transfer", description = "Embryos transfer management")
@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @Operation(summary = "Save a new embryo transfer", description = "It saves and returns a json with the new trasnfer")
    @PostMapping
    public ResponseEntity<TransferResponseDto> newTransfer(@Valid @RequestBody TransferInitialDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.newTransfer(dto));
    }

    @Operation(summary = "Get fiv's transfers", description = "It returns a json list with the fiv's transfers")
    @GetMapping
    public ResponseEntity<List<TransferResponseDto>> getTransfersByFivId(@RequestParam Long fivId){
        return ResponseEntity.status(HttpStatus.OK).body(transferService.getTransfersByFivId(fivId));
    }
}
