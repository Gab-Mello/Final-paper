package com.gabriel.pive.fiv.cultivation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabriel.pive.fiv.entities.Fiv;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cultivations")
@Entity
public class Cultivation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "cultivation")
    private Fiv fiv;

    @OneToMany(mappedBy = "embryoCultivation" )
    private List<Embryo> embryos;

    private Integer totalEmbryos;

    private Integer viableEmbryos;



}