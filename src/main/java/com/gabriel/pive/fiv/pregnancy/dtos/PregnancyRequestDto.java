package com.gabriel.pive.fiv.pregnancy.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record PregnancyRequestDto(@NotNull(message = "Id da receptora em branco.") Long receiverCattleId,
                                  @JsonProperty("is_pregnant") boolean pregnant) {
}
