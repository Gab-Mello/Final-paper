package com.gabriel.pive.fiv.cultivation.dtos;


import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.fiv.cultivation.entities.Embryo;


import java.util.List;

public record EmbryoRequestDto(boolean frozen, Long receiverCattleId) {

    public Embryo toEmbryo(ReceiverCattle receiverCattle){
        return new Embryo(
                frozen,
                receiverCattle);
    }

}
