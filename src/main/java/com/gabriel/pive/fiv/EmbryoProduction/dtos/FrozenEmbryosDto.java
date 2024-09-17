package com.gabriel.pive.fiv.EmbryoProduction.dtos;

import jakarta.validation.constraints.NotNull;

public record FrozenEmbryosDto(@NotNull(message = "Id da produção em branco.") Long productionId,
                               @NotNull(message = "Quantidade de embriões congelados em branco.") Integer embryosQuantity) {
}
