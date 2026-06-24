package com.gabriel.pive.fiv.EmbryoProduction.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FrozenEmbryosDto(@NotNull(message = "Id da produção em branco.") Long productionId,
                               @NotNull(message = "Quantidade de embriões congelados em branco.")
                               @Positive(message = "Quantidade de embriões congelados deve ser positiva.")
                               Integer embryosQuantity) {
}
