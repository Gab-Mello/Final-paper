package com.gabriel.pive.fiv.EmbryoProduction.dtos;

import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.animals.dtos.DonorCattleDto;
import com.gabriel.pive.animals.dtos.ReceiverCattleDto;
import com.gabriel.pive.fiv.EmbryoProduction.entities.Embryo;
import com.gabriel.pive.fiv.EmbryoProduction.enums.EmbryoDestiny;

import java.util.List;

public record EmbryoResponseDto(Long id,
                                EmbryoDestiny destiny,
                                BullDto bull,
                                DonorCattleDto donorCattle,
                                ReceiverCattleDto receiverCattle) {

    public static EmbryoResponseDto toEmbryoResponseDto(Embryo embryo){
        return new EmbryoResponseDto(
                embryo.getId(),
                embryo.getDestiny(),
                BullDto.toBullDto(embryo.getEmbryoBull()),
                DonorCattleDto.toDonorCattleDto(embryo.getEmbryoDonorCattle()),
                ReceiverCattleDto.toReceiverCattleDto(embryo.getEmbryoReceiverCattle()));
    }

    public static List<EmbryoResponseDto> toEmbryoResponseDtoList(List<Embryo> list){
        if (list == null){
            return null;
        }
        List<EmbryoResponseDto> listDto = list.stream().map(embryo->EmbryoResponseDto.
                toEmbryoResponseDto(embryo)).toList();
        return listDto;
    }
}
