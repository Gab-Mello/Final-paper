package com.gabriel.pive.fiv.cultivation.dtos;


import com.gabriel.pive.fiv.cultivation.entities.Cultivation;
import com.gabriel.pive.fiv.cultivation.entities.Embryo;
import com.gabriel.pive.fiv.entities.Fiv;
import java.util.List;

public record CultivationRequestDto(Long fivId,
                                    List<Embryo> embryos, Integer totalEmbryos,
                                    Integer viableEmbryos) {

    public Cultivation toCultivation(Fiv fiv){
        return new Cultivation(
                fiv,
                embryos,
                totalEmbryos,
                viableEmbryos);
    }


}
