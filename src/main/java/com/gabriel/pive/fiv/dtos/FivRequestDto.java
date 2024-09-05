package com.gabriel.pive.fiv.dtos;

import com.gabriel.pive.fiv.entities.Fiv;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FivRequestDto(
        LocalDate date,
        @NotBlank(message = "Fazenda em branco.")String farm,
        @NotBlank(message = "Laboratório em branco.") String laboratory,
        @NotBlank(message = "Cliente em branco.") String client,
        @NotBlank(message = "Veterinário em branco.") String veterinarian,
        @NotBlank(message = "Técnico em branco.") String technical,
        String TE
) {

    public Fiv toFiv(){
        return new Fiv(date, farm, laboratory, client, veterinarian, technical, TE);
    }


}
