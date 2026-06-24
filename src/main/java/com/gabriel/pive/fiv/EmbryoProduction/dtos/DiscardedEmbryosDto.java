package com.gabriel.pive.fiv.EmbryoProduction.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DiscardedEmbryosDto(@NotNull(message = "Id da produção em branco.") Long productionId,
                                  @NotNull(message = "Quantidade de embriões para descarte em branco.")
                                  @Positive(message = "Quantidade de embriões para descarte deve ser positiva.")
                                  Integer embryosQuantity) {
}
