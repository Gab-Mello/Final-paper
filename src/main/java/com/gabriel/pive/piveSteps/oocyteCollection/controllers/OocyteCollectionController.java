package com.gabriel.pive.piveSteps.oocyteCollection.controllers;

import com.gabriel.pive.piveSteps.oocyteCollection.dtos.OocyteCollectionRequestDto;
import com.gabriel.pive.piveSteps.oocyteCollection.dtos.OocyteCollectionResponseDto;
import com.gabriel.pive.piveSteps.oocyteCollection.services.OocyteCollectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oocyte-collection")
@Tag(name = "Oocyte Collection", description = "Oocyte collection management")
public class OocyteCollectionController {

    @Autowired
    private OocyteCollectionService oocyteCollectionService;

    @Operation(summary = "Save a new oocyte collection", description = "It saves and returns a json with the new collection")
    @PostMapping
    public ResponseEntity<OocyteCollectionResponseDto> newOocyteCollection(@RequestBody OocyteCollectionRequestDto dto){
        try{
            OocyteCollectionResponseDto oocyteCollection = oocyteCollectionService.newOocyteCollection(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(oocyteCollection);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @Operation(summary = "List all oocyte collections", description = "It returns a json list with all oocyte collections")
    @GetMapping
    public ResponseEntity<List<OocyteCollectionResponseDto>> getAllOocyteCollections(){
        return ResponseEntity.status(HttpStatus.OK).body(oocyteCollectionService.getAll());

    }

    @Operation(summary = "Find a oocyte collection by Id", description = "It returns a json with the oocyte collection found by Id ")
    @GetMapping("/{id}")
    public ResponseEntity<OocyteCollectionResponseDto> getOocyteCollectionById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(oocyteCollectionService.getCollectionById(id));
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @Operation(summary = "Filter oocyte collections by bull", description = "It returns a json list with the oocyte collections found by the specific bull")
    @GetMapping("/bull")
    public ResponseEntity<List<OocyteCollectionResponseDto>> filterByBull(@Parameter(description = "Example: http://localhost:8080/oocyte-collection/bull?registrationNumber=123")
                                                                      @RequestParam String registrationNumber){
        return ResponseEntity.status(HttpStatus.OK).body(oocyteCollectionService.filterByBull(registrationNumber));
    }

    @Operation(summary = "Filter oocyte collections by donor", description = "It returns a json list with the oocyte collections found by the specific donor")
    @GetMapping("/donor")
    public ResponseEntity<List<OocyteCollectionResponseDto>> filterByDonor(@Parameter(description = "Example: http://localhost:8080/oocyte-collection/donor?registrationNumber=1234")
                                                                       @RequestParam String registrationNumber){
        return ResponseEntity.status(HttpStatus.OK).body(oocyteCollectionService.filterByDonor(registrationNumber));
    }


}
