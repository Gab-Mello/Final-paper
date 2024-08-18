package com.gabriel.pive.fiv.oocyteCollection.dtos;

import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.animals.dtos.DonorCattleDto;
import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;

import java.time.LocalDate;
import java.util.List;

public record OocyteCollectionResponseDto( Fiv fiv,
                                           LocalDate date,
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
                oocyteCollection.getFiv(),
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

    public static OocyteCollection editMapper(OocyteCollection oocyteCollection, OocyteCollectionRequestDto dto,
                                                         DonorCattle donorCattle,
                                                         Bull bull){
        oocyteCollection.setDate(dto.date());
        oocyteCollection.setFarm(dto.farm());
        oocyteCollection.setLaboratory(dto.laboratory());
        oocyteCollection.setClient(dto.client());
        oocyteCollection.setVeterinarian(dto.veterinarian());
        oocyteCollection.setTechnical(dto.technical());
        oocyteCollection.setDonorCattle(donorCattle);
        oocyteCollection.setBull(bull);
        oocyteCollection.setTotalOocytes(dto.totalOocytes());
        oocyteCollection.setViableOocytes(dto.viableOocytes());
        oocyteCollection.setNonViableOocytes(dto.totalOocytes() - dto.viableOocytes());

        return oocyteCollection;
    }


}
