package com.gabriel.pive.fiv.cultivation.dtos;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.fiv.cultivation.entities.Embryo;

public record EmbryoResponseDto(Long id,Bull bull,
                                DonorCattle donorCattle,
                                ReceiverCattle receiverCattle,
                                boolean frozen) {

    public static EmbryoResponseDto toEmbryoResponseDto(Embryo embryo){
        return new EmbryoResponseDto(
                embryo.getId(),
                embryo.getEmbryoBull(),
                embryo.getEmbryoDonorCattle(),
                embryo.getEmbryoReceiverCattle(),
                embryo.isFrozen());
    }
}
