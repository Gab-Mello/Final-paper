package com.gabriel.pive.animals.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabriel.pive.fiv.EmbryoProduction.entities.Embryo;
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
@Table(name = "tb_bulls")
@Entity
public class Bull {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String registrationNumber;

    private Double averageEmbryoPercentage;

    @JsonIgnore
    @OneToMany(mappedBy = "bull")
    private List<OocyteCollection> oocyteCollections = new ArrayList<>();

    @OneToMany(mappedBy = "embryoBull")
    private List<Embryo> embryosGenerated = new ArrayList<>();

    public Bull(String name, String registrationNumber){
        this.name = name;
        this.registrationNumber = registrationNumber;
    }
}
