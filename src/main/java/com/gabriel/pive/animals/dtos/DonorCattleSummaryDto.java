package com.gabriel.pive.animals.dtos;

import com.gabriel.pive.animals.entities.DonorCattle;

import java.time.LocalDate;

public record DonorCattleSummaryDto(Long id, String name, String breed, LocalDate birth, String registrationNumber) {

    public static DonorCattleDto toDonorCattleSummaryDto(DonorCattle donorCattle){
        return new DonorCattleDto(
                donorCattle.getId(),
                donorCattle.getName(),
                donorCattle.getBreed(),
                donorCattle.getBirth(),
                donorCattle.getRegistrationNumber()
        );
}}
