package com.gabriel.pive.fiv.EmbryoProduction.controllers;

import com.gabriel.pive.fiv.EmbryoProduction.dtos.TransferInitialDto;
import com.gabriel.pive.fiv.EmbryoProduction.services.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Embryos transfer", description = "Embryos transfer management")
@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @Operation(summary = "Save a new embryo transfer", description = "It saves and returns a json with the new trasnfer")
    @PostMapping
    public ResponseEntity<TransferInitialDto> newTransfer(@RequestBody TransferInitialDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.newTransfer(dto));
    }

    @Operation(summary = "Get fiv's transfers", description = "It returns a json list with the fiv's transfers")
    @GetMapping
    public ResponseEntity<List<TransferInitialDto>> getTransfersByFivId(@RequestParam Long fivId){
        return ResponseEntity.status(HttpStatus.OK).body(transferService.getTransfersByFivId(fivId));
    }
}
