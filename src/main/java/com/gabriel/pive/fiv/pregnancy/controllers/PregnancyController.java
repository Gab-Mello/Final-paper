package com.gabriel.pive.fiv.pregnancy.controllers;

import com.gabriel.pive.animals.dtos.ReceiverCattleDto;
import com.gabriel.pive.animals.services.ReceiverCattleService;
import com.gabriel.pive.fiv.pregnancy.dtos.PregnancyRequestDto;
import com.gabriel.pive.fiv.pregnancy.services.PregnancyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pregnancy", description = "Pregnancy management")
@RestController
@RequestMapping("/pregnancy")
@RequiredArgsConstructor
public class PregnancyController {

    private final PregnancyService pregnancyService;
    private final ReceiverCattleService receiverCattleService;

    @Operation(summary = "Confirm a pregnancy", description = "Confirm or not if the receiver is pregnant.")
    @PostMapping
    public ResponseEntity<?> confirmPregnancy(@Valid @RequestBody PregnancyRequestDto dto){
        pregnancyService.confirmPregnancy(dto.receiverCattleId(), dto.pregnant());
        return ResponseEntity.status(HttpStatus.OK).body("Pregnancy saved.");
    }

    @Operation(summary = "Find in progress pregnant receivers", description = "It returns a json list with all in-progress pregnant receivers from the specified fiv.")
    @GetMapping("/in-progress-receivers/{fivId}")
    public ResponseEntity<List<ReceiverCattleDto>> getInProgressPregnantReceivers(@PathVariable Long fivId){
        return ResponseEntity.status(HttpStatus.OK).body(receiverCattleService.getAllInProgressPregnantReceivers(fivId));
    }
}
