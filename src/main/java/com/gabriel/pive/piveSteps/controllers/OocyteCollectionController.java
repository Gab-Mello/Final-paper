package com.gabriel.pive.piveSteps.controllers;

import com.gabriel.pive.piveSteps.dtos.OocyteCollectionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oocyte-collection")
public class OocyteCollectionController {



    public ResponseEntity<OocyteCollectionDto> newOocyteCollection(@RequestBody OocyteCollectionDto dto){

        return ResponseEntity.status(HttpStatus.CREATED).body();
    }
}
