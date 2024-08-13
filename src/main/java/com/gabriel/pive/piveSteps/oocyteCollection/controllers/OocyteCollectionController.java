package com.gabriel.pive.piveSteps.oocyteCollection.controllers;

import com.gabriel.pive.piveSteps.oocyteCollection.dtos.OocyteCollectionDto;
import com.gabriel.pive.piveSteps.oocyteCollection.entities.OocyteCollection;
import com.gabriel.pive.piveSteps.oocyteCollection.services.OocyteCollectionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oocyte-collection")
public class OocyteCollectionController {

    @Autowired
    private OocyteCollectionService oocyteCollectionService;

    @PostMapping
    public ResponseEntity<OocyteCollectionDto> newOocyteCollection(@RequestBody OocyteCollectionDto dto){
        try{
            OocyteCollectionDto oocyteCollection = oocyteCollectionService.newOocyteCollection(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(oocyteCollection);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping
    public ResponseEntity<List<OocyteCollectionDto>> getAllOocyteCollections(){
        return ResponseEntity.status(HttpStatus.OK).body(oocyteCollectionService.getAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<OocyteCollectionDto> getOocyteCollectionById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(oocyteCollectionService.getCollectionById(id));
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
