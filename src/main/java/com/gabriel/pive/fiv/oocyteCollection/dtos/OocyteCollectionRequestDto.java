package com.gabriel.pive.fiv.oocyteCollection.dtos;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;
import com.gabriel.pive.infra.validation.ViableNotExceedingTotal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

@ViableNotExceedingTotal
public record OocyteCollectionRequestDto(
        @NotNull(message = "FivId em branco.")Long fivId,
        @NotNull(message = "Doadora em branco.") Long donorCattleId,
        Long bullId,
        @NotNull(message = "Total de oócitos em branco.")
        @PositiveOrZero(message = "Total de oócitos deve ser zero ou positivo.")
        Integer totalOocytes,
        @NotNull(message = "Oócitos viáveis em branco.")
        @PositiveOrZero(message = "Oócitos viáveis deve ser zero ou positivo.")
        Integer viableOocytes,
        boolean finished
                                         ) {

    public OocyteCollection toOocyteCollection(Fiv fiv, DonorCattle donorCattle, Bull bull){
        return new OocyteCollection(
                fiv,
                donorCattle,
                bull,
                totalOocytes,
                viableOocytes
                );
    }


}
