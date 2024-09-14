package com.gabriel.pive.fiv.EmbryoProduction.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;
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
@Table(name = "tb_productions")
@Entity
public class EmbryoProduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "embryoProduction")
    private OocyteCollection oocyteCollection;

    @OneToMany(mappedBy = "embryoEmbryoProduction" )
    private List<Embryo> embryos = new ArrayList<>();

    private Integer totalEmbryos;

    private Float embryosPercentage;

    public EmbryoProduction(OocyteCollection oocyteCollection, Integer totalEmbryos){
        this.oocyteCollection = oocyteCollection;
        this.totalEmbryos = totalEmbryos;
    }
}
