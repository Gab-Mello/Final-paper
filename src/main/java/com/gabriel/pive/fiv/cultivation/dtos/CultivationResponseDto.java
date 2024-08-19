package com.gabriel.pive.fiv.cultivation.dtos;

import com.gabriel.pive.fiv.cultivation.entities.Embryo;

import java.util.List;

public record CultivationResponseDto(Long id, Long fivId, List<Embryo> embryos, Integer totalEmbryos, Integer viableEmbryos) {



}
