package com.gabriel.pive.piveSteps.oocyteCollection.controllers;

import com.gabriel.pive.piveSteps.oocyteCollection.dtos.OocyteCollectionDto;
import com.gabriel.pive.piveSteps.oocyteCollection.services.OocyteCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oocyte-collection")
public class OocyteCollectionController {

    @Autowired
    private OocyteCollectionService oocyteCollectionService;

    @PostMapping
    public ResponseEntity<OocyteCollectionDto> newOocyteCollection(@RequestBody OocyteCollectionDto dto){
        if (oocyteCollectionService.newOocyteCollection(dto) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(oocyteCollectionService.newOocyteCollection(dto));
    }
}
