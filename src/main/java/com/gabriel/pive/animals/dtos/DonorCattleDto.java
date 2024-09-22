package com.gabriel.pive.animals.dtos;

import com.gabriel.pive.animals.entities.DonorCattle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

public record DonorCattleDto(Long id,
                             String name,
                             String breed,
                             LocalDate birth,
                             @NotBlank(message = "Número de identificação em branco.")String registrationNumber,
                             String averageViableOocytes,
                             String averageEmbryoPercentage) {

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

        Double averageEmbryoPercentage = donorCattle.getAverageEmbryoPercentage();
        Double averageViableOocytes = donorCattle.getAverageViableOocytes();
        String formattedEmbryoPercentage;
        String formattedViableOocytes;

        if (averageEmbryoPercentage == null || averageEmbryoPercentage == 0){
            formattedEmbryoPercentage = "0.00%";
        }
        else{
            DecimalFormat df = new DecimalFormat("#.00");
            formattedEmbryoPercentage = df.format(averageEmbryoPercentage) + "%";
        }

        if (averageViableOocytes == null || averageViableOocytes == 0){
            formattedViableOocytes = "0";
        }
        else{
            DecimalFormat df = new DecimalFormat("#.00");
            formattedViableOocytes = df.format(averageViableOocytes);
        }

        return new DonorCattleDto(
                donorCattle.getId(),
                donorCattle.getName(),
                donorCattle.getBreed(),
                donorCattle.getBirth(),
                donorCattle.getRegistrationNumber(),
                formattedViableOocytes,
                formattedEmbryoPercentage
        );
    }
    public static List<DonorCattleDto> toDonorCattleDtoList(List<DonorCattle> list){
        List<DonorCattleDto> listDto = list.stream().
                map(donorCattle -> DonorCattleDto.toDonorCattleDto(donorCattle)).
                toList();

        return listDto;
    }

}
