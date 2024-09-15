package com.gabriel.pive.fiv.EmbryoProduction.dtos;


import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.EmbryoProduction.entities.Embryo;
import com.gabriel.pive.fiv.EmbryoProduction.enums.EmbryoDestiny;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;

public record EmbryoRequestDto(@NotNull(message = "Id da produção em branco.") Long productionId,
                               @NotNull(message = "Data em branco.") LocalDate date,
                               @NotNull(message = "Destino em branco") EmbryoDestiny destiny,
                               Long receiverCattleId) {

    public Embryo toEmbryo(EmbryoProduction embryoProduction){
        return new Embryo(
                embryoProduction,
                destiny
                );
    }

}
