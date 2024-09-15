package com.gabriel.pive.fiv.EmbryoProduction.controllers;

import com.gabriel.pive.fiv.EmbryoProduction.dtos.TransferInitialDto;
import com.gabriel.pive.fiv.EmbryoProduction.services.TransferService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Embryos transfer", description = "Embryos transfer management")
@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping
    public ResponseEntity<TransferInitialDto> newTransfer(@RequestBody TransferInitialDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.newTransfer(dto));
    }
}
