package com.gabriel.pive.fiv.EmbryoProduction.controllers;

import com.gabriel.pive.fiv.EmbryoProduction.dtos.EmbryoRequestDto;
import com.gabriel.pive.fiv.EmbryoProduction.dtos.EmbryoResponseDto;
import com.gabriel.pive.fiv.EmbryoProduction.dtos.TransferDataDto;
import com.gabriel.pive.fiv.EmbryoProduction.dtos.TransferResponseDto;
import com.gabriel.pive.fiv.EmbryoProduction.services.EmbryosService;
import com.gabriel.pive.fiv.EmbryoProduction.services.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Embryos", description = "Embryos management")
@RestController
@RequestMapping("/embryo")
public class EmbryoController {

    @Autowired
    private EmbryosService embryosService;

    @Autowired
    private TransferService transferService;

   /* @Operation(summary = "Save a new embryo", description = "It saves and returns a json with the new embryo")
    @PostMapping
    public ResponseEntity<?> saveEmbryo(@Valid @RequestBody EmbryoRequestDto dto){
        EmbryoResponseDto embryo = embryosService
                .saveEmbryo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(embryo);
    }*/


    @Operation(summary = "Save a embryo in a transfer", description = "It saves and returns a json with the transfer")
    @PostMapping("/transfer")
    public ResponseEntity<TransferResponseDto> saveEmbryoInTransfer(@Valid @RequestBody TransferDataDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.saveTransferData(dto));
    }

//    @Operation(summary = "edit a embryo by Id", description = "It returns a json with the new embryo")
//    @PutMapping("/{id}")
//    public ResponseEntity<?> editEmbryo(@PathVariable Long id, @Valid @RequestBody EmbryoRequestDto dto){
//        EmbryoResponseDto embryo = embryosService.editEmbryo(id,dto);
//        return ResponseEntity.status(HttpStatus.OK).body(embryo);
//        }

    @Operation(summary = "Get all embryos", description = "It returns a json list with all embryos")
    @GetMapping
    public ResponseEntity<List<EmbryoResponseDto>> getAllEmbryos(){
        return ResponseEntity.status(HttpStatus.OK).body(embryosService.getAllEmbryos());
    }

    @Operation(summary = "Get a embryo by Id", description = "It returns a json with the embryo")
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmbryoById(@PathVariable Long id){
            return ResponseEntity.status(HttpStatus.OK).body(embryosService.getEmbryoById(id));
    }

    }
