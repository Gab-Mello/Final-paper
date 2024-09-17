package com.gabriel.pive.fiv.EmbryoProduction.dtos;

import jakarta.validation.constraints.NotNull;

public record DiscardedEmbryosDto(@NotNull(message = "Id da produção em branco.") Long productionId,
                                  @NotNull(message = "Quantidade de embriões para descarte em branco.") Integer embryosQuantity) {
}
