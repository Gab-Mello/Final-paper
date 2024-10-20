package com.gabriel.pive.fiv.dtos;

import com.gabriel.pive.fiv.EmbryoProduction.dtos.CultivationSummaryResponseDto;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.enums.FivStatusEnum;
import com.gabriel.pive.fiv.oocyteCollection.dtos.OocyteCollectionResponseDto;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

public record FivResponseDto(Long id, FivStatusEnum status,
                             LocalDate date, String farm,
                             String laboratory, String client,
                             String veterinarian, String technical,
                             String TE, List<OocyteCollectionResponseDto> oocyteCollections,
                             Integer fivTotalOocytesCollected, Integer fivTotalViableOocytesCollected,
                             Integer fivTotalEmbryos, String fivEmbryosPercentage,
                             Integer fivEmbryosRegistered,
                             Integer fivNumberTransferredEmbryos,
                             Integer fivNumberFrozenEmbryos,
                             Integer fivNumberDiscardedEmbryos,
                             Integer fivNumberPregnancies,
                             String fivPregnancyPercentage) {


    public static FivResponseDto toFivResponseDto(Fiv fiv){

        String formattedEmbryosPercentage = formatPercentage(fiv.getEmbryosPercentage());
        String formattedPregnancyPercentage = formatPercentage(fiv.getFivPregnancyPercentage());

        return new FivResponseDto(
                fiv.getId(),
                fiv.getStatus(),
                fiv.getDate(),
                fiv.getFarm(),
                fiv.getLaboratory(),
                fiv.getClient(),
                fiv.getVeterinarian(),
                fiv.getTechnical(),
                fiv.getTE(),
                OocyteCollectionResponseDto.toOocyteCollectionDtoList(fiv.getOocyteCollections()),
                fiv.getTotalOocytesCollected(),
                fiv.getTotalViableOocytesCollected(),
                fiv.getTotalEmbryos(),
                formattedEmbryosPercentage,
                fiv.getEmbryosRegistered(),
                fiv.getFivTransferredEmbryosNumber(),
                fiv.getFivFrozenEmbryosNumber(),
                fiv.getFivDiscardedEmbryosNumber(),
                fiv.getFivTotalPregnancy(),
                formattedPregnancyPercentage

        );
    }

    public static List<FivResponseDto> toFivResponseDtoList(List<Fiv> list){
        List<FivResponseDto> listDto = list.stream().map(fiv -> toFivResponseDto(fiv)).toList();
        return listDto;
    }

    public static String formatPercentage(Float percentage){
        if (percentage == null || percentage == 0){
            return  "0.00%";
        }
        else{
            DecimalFormat df = new DecimalFormat("#.00");
            return df.format(percentage) + "%";
        }
    }
}
