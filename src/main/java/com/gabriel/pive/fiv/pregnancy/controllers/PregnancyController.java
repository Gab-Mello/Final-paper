package com.gabriel.pive.fiv.pregnancy.controllers;

import com.gabriel.pive.animals.dtos.ReceiverCattleDto;
import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.animals.exceptions.ReceiverCattleNotFoundException;
import com.gabriel.pive.animals.repositories.ReceiverCattleRepository;
import com.gabriel.pive.animals.services.ReceiverCattleService;
import com.gabriel.pive.fiv.pregnancy.dtos.PregnancyRequestDto;
import com.gabriel.pive.fiv.pregnancy.services.PregnancyService;
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

    @PostMapping
    public ResponseEntity<?> confirmPregnancy(@RequestBody PregnancyRequestDto dto){
        ReceiverCattle receiverCattle = receiverCattleRepository.findById(dto.receiverCattleId())
                .orElseThrow(ReceiverCattleNotFoundException::new);

        pregnancyService.confirmPregnancy(receiverCattle, dto.is_pregnant());
        return ResponseEntity.status(HttpStatus.OK).body("Pregnancy saved.");
    }

    @GetMapping("/in-progress-receivers")
    public ResponseEntity<List<ReceiverCattleDto>> getInProgressPregnantReceivers(@RequestBody Long fivId){
        return ResponseEntity.status(HttpStatus.OK).body(receiverCattleService.getAllInProgressPregnantReceivers(fivId));
    }
}
