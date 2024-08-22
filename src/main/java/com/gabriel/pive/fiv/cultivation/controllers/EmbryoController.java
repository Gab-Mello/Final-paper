package com.gabriel.pive.fiv.cultivation.controllers;

import com.gabriel.pive.animals.exceptions.ReceiverCattleNotFoundException;
import com.gabriel.pive.fiv.cultivation.dtos.CultivationRequestDto;
import com.gabriel.pive.fiv.cultivation.dtos.CultivationResponseDto;
import com.gabriel.pive.fiv.cultivation.dtos.EmbryoRequestDto;
import com.gabriel.pive.fiv.cultivation.dtos.EmbryoResponseDto;
import com.gabriel.pive.fiv.cultivation.exceptions.CultivationNotFoundException;
import com.gabriel.pive.fiv.cultivation.exceptions.ReceiverCattleAlreadyHasEmbryoException;
import com.gabriel.pive.fiv.cultivation.services.EmbryosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Embryos", description = "Embryos management")
@RestController
@RequestMapping("/embryo")
public class EmbryoController {

    @Autowired
    private EmbryosService embryosService;

    @Operation(summary = "Save a new embryo", description = "It saves and returns a json with the new embryo")
    @PostMapping
    public ResponseEntity<?> saveEmbryo(@RequestBody EmbryoRequestDto dto){
         try{
             EmbryoResponseDto embryo = embryosService.saveEmbryo(dto);
             return ResponseEntity.status(HttpStatus.CREATED).body(embryo);
         }
         catch (ReceiverCattleNotFoundException | ReceiverCattleAlreadyHasEmbryoException |
                CultivationNotFoundException e){
             return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
         }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editEmbryo(@PathVariable Long id, @RequestBody EmbryoRequestDto dto){
        try{
            EmbryoResponseDto embryo = embryosService.editEmbryo(id,dto);
            return ResponseEntity.status(HttpStatus.OK).body(embryo);
        }
        catch (EntityNotFoundException e){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("Receiver cattle already has an embryo.");
        }
        }

    @Operation(summary = "Get all embryos", description = "It returns a json list with all embryos")
    @GetMapping
    public ResponseEntity<List<EmbryoResponseDto>> getAllEmbryos(){
        return ResponseEntity.status(HttpStatus.OK).body(embryosService.getAllEmbryos());
    }
    }
