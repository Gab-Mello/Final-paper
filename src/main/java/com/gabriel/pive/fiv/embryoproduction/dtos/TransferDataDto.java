package com.gabriel.pive.fiv.embryoproduction.dtos;


import jakarta.validation.constraints.NotNull;

public record TransferDataDto(@NotNull(message = "Id da produção em branco.") Long productionId,
                              @NotNull(message = "Id da transferência em branco.") Long transferId,
                              @NotNull(message = "Id da receptora em branco.") Long receiverId) {
}
