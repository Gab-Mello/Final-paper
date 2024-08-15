package com.gabriel.pive.piveSteps.oocyteCollection.dtos;

import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.animals.dtos.DonorCattleDto;
import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.piveSteps.oocyteCollection.entities.OocyteCollection;

import java.time.LocalDate;
import java.util.List;

public record OocyteCollectionResponseDto( LocalDate date,
                                           String farm,
                                           String laboratory,
                                           String client,
                                           String veterinarian,
                                           String technical,
                                           DonorCattleDto donorCattle,
                                           BullDto bull,
                                           Integer totalOocytes,
                                           Integer viableOocytes,
                                           Integer nonViableOocytes
) {

    public static OocyteCollectionResponseDto toOocyteCollectionDto(OocyteCollection oocyteCollection){
        return new OocyteCollectionResponseDto(
                oocyteCollection.getDate(),
                oocyteCollection.getFarm(),
                oocyteCollection.getLaboratory(),
                oocyteCollection.getClient(),
                oocyteCollection.getVeterinarian(),
                oocyteCollection.getTechnical(),
                DonorCattleDto.toDonorCattleDto(oocyteCollection.getDonorCattle()),
                BullDto.toBullDto(oocyteCollection.getBull()),
                oocyteCollection.getTotalOocytes(),
                oocyteCollection.getViableOocytes(),
                oocyteCollection.getNonViableOocytes());
    }

    public static List<OocyteCollectionResponseDto> toOocyteCollectionDtoList(List<OocyteCollection> list){
        List<OocyteCollectionResponseDto> listDto = list.stream().map(collection -> toOocyteCollectionDto(collection)).toList();
        return listDto;
    }


}
