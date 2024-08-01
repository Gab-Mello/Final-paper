package com.gabriel.pive.controllers;

import com.gabriel.pive.dtos.ReceiverCattleDto;
import com.gabriel.pive.services.ReceiverCattleService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("receiver")
public class ReceiverCattleController {

    @Autowired
    private ReceiverCattleService receiverCattleService;

    @PostMapping
    public ResponseEntity<ReceiverCattleDto> create(@Valid @RequestBody ReceiverCattleDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(receiverCattleService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ReceiverCattleDto>> listReceivers(){
        return ResponseEntity.status(HttpStatus.OK).body(receiverCattleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiverCattleDto> getReceiverCattleById(@PathVariable Long id){
        if (receiverCattleService.findById(id)==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(receiverCattleService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReceiverCattleDto> deleteReceiverCattle(@PathVariable Long id){
        if (receiverCattleService.findById(id)==null){
            return ResponseEntity.notFound().build();
        }
        receiverCattleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceiverCattleDto> editReceiverCattle(@PathVariable Long id, @RequestBody ReceiverCattleDto dto){
        if (receiverCattleService.findById(id)==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(receiverCattleService.edit(id,dto));
    }



}
