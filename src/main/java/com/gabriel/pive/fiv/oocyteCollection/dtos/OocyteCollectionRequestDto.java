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
        @NotNull(message = "Data em branco.") LocalDate date,
        @NotBlank(message = "Fazenda em branco.")String farm,
        @NotBlank(message = "Laboratório em branco.") String laboratory,
        @NotBlank(message = "Cliente em branco.") String client,
        @NotBlank(message = "Veterinário em branco.") String veterinarian,
        @NotBlank(message = "Técnico em branco.") String technical,
        @NotNull(message = "Doadora em branco.") Long donorCattleId,
        @NotNull(message = "Touro em branco.")  Long bullId,
        @NotNull(message = "Total de oócitos em branco.")Integer totalOocytes,
        @NotNull(message = "Oócitos viáveis em branco.")Integer viableOocytes
                                         ) {

    public OocyteCollection toOocyteCollection(DonorCattle donorCattle, Bull bull){
        return new OocyteCollection(
                donorCattle,
                bull,
                farm,
                laboratory,
                client,
                veterinarian,
                technical,
                totalOocytes,
                viableOocytes,
                totalOocytes - viableOocytes,
                date);
    }


}
