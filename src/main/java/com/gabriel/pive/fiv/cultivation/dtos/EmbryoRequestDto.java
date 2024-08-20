package com.gabriel.pive.fiv.cultivation.dtos;


import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.fiv.cultivation.entities.Cultivation;
import com.gabriel.pive.fiv.cultivation.entities.Embryo;


import java.util.List;

public record EmbryoRequestDto(Long CultivationId, boolean frozen, Long receiverCattleId) {

    public Embryo toEmbryo(ReceiverCattle receiverCattle, Cultivation cultivation){
        return new Embryo(
                cultivation,
                frozen,
                receiverCattle);
    }

}
