package com.gabriel.pive.fiv.oocytecollection.dtos;

import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.animals.dtos.DonorCattleDto;
import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.fiv.embryoproduction.dtos.ProductionSummaryDto;
import com.gabriel.pive.fiv.embryoproduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.oocytecollection.entities.OocyteCollection;

import java.time.LocalDate;
import java.util.List;

public record OocyteCollectionResponseDto( Long id,
                                           DonorCattleDto donorCattle,
                                           BullDto bull,
                                           Integer totalOocytes,
                                           Integer viableOocytes,
                                           ProductionSummaryDto embryoProduction
) {

    public static OocyteCollectionResponseDto toOocyteCollectionDto(OocyteCollection oocyteCollection){

        if (oocyteCollection == null){
            return null;
        }

        return new OocyteCollectionResponseDto(
                oocyteCollection.getId(),
                DonorCattleDto.toDonorCattleDto(oocyteCollection.getDonorCattle()),
                BullDto.toBullDto(oocyteCollection.getBull()),
                oocyteCollection.getTotalOocytes(),
                oocyteCollection.getViableOocytes(),
                ProductionSummaryDto.toProductionSummaryDto(oocyteCollection.getEmbryoProduction()));
    }

    public static List<OocyteCollectionResponseDto> toOocyteCollectionDtoList(List<OocyteCollection> list){
        if (list == null){
            return null;
        }
        List<OocyteCollectionResponseDto> listDto = list.stream().map(collection -> toOocyteCollectionDto(collection)).toList();
        return listDto;
    }
}
