package com.gabriel.pive.fiv.oocyteCollection.dtos;

import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.animals.dtos.BullSummaryDto;
import com.gabriel.pive.animals.dtos.DonorCattleDto;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;

import java.time.LocalDate;

public record OocyteCollectionPostDto(Long fivId,
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
                                      Integer nonViableOocytes) {

//    public static OocyteCollectionPostDto toOocyteCollectionPostDto(OocyteCollection oocyteCollection, Long fivId){
//
//        if (oocyteCollection == null){
//            return null;
//        }
//
//        return new OocyteCollectionPostDto(
//                fivId,
//                oocyteCollection.getDate(),
//                oocyteCollection.getFarm(),
//                oocyteCollection.getLaboratory(),
//                oocyteCollection.getClient(),
//                oocyteCollection.getVeterinarian(),
//                oocyteCollection.getTechnical(),
//                DonorCattleSummaryDto.toDonorCattleSummaryDto(oocyteCollection.getDonorCattle()),
//                BullSummaryDto.toBullSummaryDto(oocyteCollection.getBull()),
//                oocyteCollection.getTotalOocytes(),
//                oocyteCollection.getViableOocytes(),
//                oocyteCollection.getNonViableOocytes());
//    }
}
