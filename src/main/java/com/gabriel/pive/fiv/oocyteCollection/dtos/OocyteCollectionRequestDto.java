package com.gabriel.pive.fiv.oocyteCollection.dtos;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record OocyteCollectionRequestDto(
        @NotNull(message = "FivId em branco.")Long fivId,
        @NotNull(message = "Doadora em branco.") Long donorCattleId,
        @NotNull(message = "Touro em branco.")  Long bullId,
        @NotNull(message = "Total de oócitos em branco.")Integer totalOocytes,
        @NotNull(message = "Oócitos viáveis em branco.")Integer viableOocytes,
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
