package com.gabriel.pive.controllers;

import com.gabriel.pive.dtos.ReceiverCattleDto;
import com.gabriel.pive.services.ReceiverCattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("receiver")
public class ReceiverCattleController {

    @Autowired
    private ReceiverCattleService receiverCattleService;

    @PostMapping
    public ResponseEntity<ReceiverCattleDto> create(@RequestBody ReceiverCattleDto dto){
        ReceiverCattleDto receiverCattleDto = receiverCattleService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(receiverCattleDto);
    }

}
