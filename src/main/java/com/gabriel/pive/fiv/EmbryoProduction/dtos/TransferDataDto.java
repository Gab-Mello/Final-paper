package com.gabriel.pive.fiv.EmbryoProduction.dtos;

import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;

public record TransferDataDto(Long productionId, Long transferId, Long receiverId) {
}
