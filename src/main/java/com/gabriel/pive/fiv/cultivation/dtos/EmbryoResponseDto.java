package com.gabriel.pive.fiv.cultivation.dtos;

import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.animals.dtos.DonorCattleDto;
import com.gabriel.pive.animals.dtos.ReceiverCattleDto;
import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.fiv.cultivation.entities.Embryo;

public record EmbryoResponseDto(Long id, BullDto bull,
                                DonorCattleDto donorCattle,
                                ReceiverCattleDto receiverCattle,
                                boolean frozen) {

    public static EmbryoResponseDto toEmbryoResponseDto(Embryo embryo){
        return new EmbryoResponseDto(
                embryo.getId(),
                BullDto.toBullDto(embryo.getEmbryoBull()),
                DonorCattleDto.toDonorCattleDto(embryo.getEmbryoDonorCattle()),
                ReceiverCattleDto.toReceiverCattleDto(embryo.getEmbryoReceiverCattle()),
                embryo.isFrozen());
    }
}
