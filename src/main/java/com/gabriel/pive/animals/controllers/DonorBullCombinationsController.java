package com.gabriel.pive.animals.controllers;

import com.gabriel.pive.animals.dtos.DonorBullCombinationDto;
import com.gabriel.pive.animals.services.DonorBullCombinationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/donor-bull-combinations")
@Tag(name = "Donor and bull combinations", description = "get best donors and bulls combinations")
public class DonorBullCombinationsController {

    @Autowired
    private DonorBullCombinationService donorBullCombinationService;

    @GetMapping
    public ResponseEntity<List<DonorBullCombinationDto>> getBestDonorBullCombinations(){
        List<DonorBullCombinationDto> bestCombinations = donorBullCombinationService.getBestDonorBullCombinations();
        return ResponseEntity.status(HttpStatus.OK).body(bestCombinations);
    }
}
