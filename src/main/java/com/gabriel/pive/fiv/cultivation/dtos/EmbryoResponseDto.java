package com.gabriel.pive.fiv.cultivation.dtos;

import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.fiv.cultivation.entities.Embryo;

public record EmbryoResponseDto(boolean frozen, ReceiverCattle receiverCattle) {

    public static EmbryoResponseDto toEmbryoResponseDto(Embryo embryo){
        return new EmbryoResponseDto(
                embryo.isFrozen(),
                embryo.getEmbryoReceiverCattle());
    }
}
