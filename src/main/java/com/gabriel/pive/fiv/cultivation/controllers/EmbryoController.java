package com.gabriel.pive.fiv.cultivation.controllers;

import com.gabriel.pive.fiv.cultivation.dtos.CultivationRequestDto;
import com.gabriel.pive.fiv.cultivation.dtos.CultivationResponseDto;
import com.gabriel.pive.fiv.cultivation.dtos.EmbryoRequestDto;
import com.gabriel.pive.fiv.cultivation.dtos.EmbryoResponseDto;
import com.gabriel.pive.fiv.cultivation.services.EmbryosService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Embryos", description = "Embryos management")
@RestController
@RequestMapping("/embryo")
public class EmbryoController {

    @Autowired
    private EmbryosService embryosService;

    @PostMapping
    public ResponseEntity<EmbryoResponseDto> saveEmbryo(@RequestBody EmbryoRequestDto dto){
         try{
             EmbryoResponseDto embryo = embryosService.saveEmbryo(dto);
             return ResponseEntity.status(HttpStatus.CREATED).body(embryo);
         }
         catch (EntityNotFoundException e){
             return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }

    }
    @PutMapping("/{id}")
    public ResponseEntity<EmbryoResponseDto> editEmbryo(@PathVariable Long id, @RequestBody EmbryoRequestDto dto){
        try{
            EmbryoResponseDto embryo = embryosService.editEmbryo(id,dto);
            return ResponseEntity.status(HttpStatus.OK).body(embryo);
        }
        catch (EntityNotFoundException e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        }
    }
