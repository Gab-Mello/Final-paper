package com.gabriel.pive.fiv.cultivation.dtos;


import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.fiv.cultivation.entities.Cultivation;
import com.gabriel.pive.fiv.cultivation.entities.Embryo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;
import java.util.List;

public record EmbryoRequestDto(@NotNull(message = "Id do cultivo em branco.") Long cultivationId,
                               @NotNull(message = "Data em branco.") LocalDate date,
                               @NotNull(message = "Congelamento em branco.") boolean frozen,
                               Long receiverCattleId) {

    public Embryo toEmbryo(ReceiverCattle receiverCattle, Cultivation cultivation){
        return new Embryo(
                cultivation,
                frozen,
                receiverCattle);
    }

}
