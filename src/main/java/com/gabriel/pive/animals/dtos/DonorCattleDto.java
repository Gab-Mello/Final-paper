package com.gabriel.pive.animals.dtos;

import com.gabriel.pive.animals.entities.DonorCattle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record DonorCattleDto(Long id,
                             @NotBlank(message = "Nome da doadora em branco.") String name,
                             @NotBlank(message = "Raça em branco.") String breed,
                             LocalDate birth,
                             @NotBlank(message = "Número de identificação em branco.")String registrationNumber) {

    public DonorCattle toDonorCattle(){
        return new DonorCattle(
                name,
                breed,
                birth,
                registrationNumber);
    }

    public static DonorCattleDto toDonorCattleDto(DonorCattle donorCattle){

        if (donorCattle == null){
            return null;
        }

        return new DonorCattleDto(
                donorCattle.getId(),
                donorCattle.getName(),
                donorCattle.getBreed(),
                donorCattle.getBirth(),
                donorCattle.getRegistrationNumber()
        );
    }
    public static List<DonorCattleDto> toDonorCattleDtoList(List<DonorCattle> list){
        List<DonorCattleDto> listDto = list.stream().
                map(donorCattle -> DonorCattleDto.toDonorCattleDto(donorCattle)).
                toList();

        return listDto;
    }

}
