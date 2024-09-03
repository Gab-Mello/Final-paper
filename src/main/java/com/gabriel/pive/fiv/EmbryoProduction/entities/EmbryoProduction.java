package com.gabriel.pive.fiv.EmbryoProduction.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabriel.pive.fiv.entities.Fiv;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cultivations")
@Entity
public class EmbryoProduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "embryoProduction")
    private Fiv fiv;

    @OneToMany(mappedBy = "embryoEmbryoProduction" )
    private List<Embryo> embryos = new ArrayList<>();

    private Integer totalEmbryos;

    private Integer viableEmbryos;

    public EmbryoProduction(Fiv fiv, Integer totalEmbryos, Integer viableEmbryos){
        this.fiv = fiv;
        this.totalEmbryos = totalEmbryos;
        this.viableEmbryos = viableEmbryos;

    }
}
