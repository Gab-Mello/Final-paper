package com.gabriel.pive.fiv.pregnancy.controllers;

import com.gabriel.pive.animals.dtos.ReceiverCattleDto;
import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.animals.exceptions.ReceiverCattleNotFoundException;
import com.gabriel.pive.animals.repositories.ReceiverCattleRepository;
import com.gabriel.pive.animals.services.ReceiverCattleService;
import com.gabriel.pive.fiv.pregnancy.dtos.PregnancyRequestDto;
import com.gabriel.pive.fiv.pregnancy.services.PregnancyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pregnancy", description = "Pregnancy management")
@RestController
@RequestMapping("/pregnancy")
public class PregnancyController {

    @Autowired
    private PregnancyService pregnancyService;

    @Autowired
    private ReceiverCattleRepository receiverCattleRepository;

    @Autowired
    private ReceiverCattleService receiverCattleService;

    @Operation(summary = "Confirm a pregnancy", description = "Confirm or not if the receiver is pregnant.")
    @PostMapping
    public ResponseEntity<?> confirmPregnancy(@RequestBody PregnancyRequestDto dto){
        ReceiverCattle receiverCattle = receiverCattleRepository.findById(dto.receiverCattleId())
                .orElseThrow(ReceiverCattleNotFoundException::new);

        pregnancyService.confirmPregnancy(receiverCattle, dto.is_pregnant());
        return ResponseEntity.status(HttpStatus.OK).body("Pregnancy saved.");
    }

    @Operation(summary = "Find in progress pregnant receivers", description = "It returns a json list with all in-progress pregnant receivers from the specified fiv.")
    @GetMapping("/in-progress-receivers/{fivId}")
    public ResponseEntity<List<ReceiverCattleDto>> getInProgressPregnantReceivers(@PathVariable Long fivId){
        return ResponseEntity.status(HttpStatus.OK).body(receiverCattleService.getAllInProgressPregnantReceivers(fivId));
    }
}
