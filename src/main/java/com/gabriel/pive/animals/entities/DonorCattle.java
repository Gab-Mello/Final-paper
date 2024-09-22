package com.gabriel.pive.animals.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabriel.pive.fiv.EmbryoProduction.entities.Embryo;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_donors")
@Entity
public class DonorCattle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String breed;

    private LocalDate birth;

    private String registrationNumber;

    private Double averageViableOocytes;

    private Double averageEmbryoPercentage;

    @JsonIgnore
    @OneToMany(mappedBy = "donorCattle", fetch = FetchType.EAGER)
    private List<OocyteCollection> oocyteCollections = new ArrayList<>();

    @OneToMany(mappedBy = "embryoDonorCattle")
    private List<Embryo> embryosGenerated = new ArrayList<>();

    public DonorCattle(String name, String breed, LocalDate birth, String registrationNumber){
        this.name = name;
        this.breed = breed;
        this.birth = birth;
        this.registrationNumber = registrationNumber;
        this.averageViableOocytes = 0.0;
        this.averageEmbryoPercentage = 0.0;
    }

    public void updateAverageViableOocytes() {
        if (oocyteCollections != null && !oocyteCollections.isEmpty()) {
            double totalViableOocytes = oocyteCollections.stream()
                    .mapToInt(OocyteCollection::getViableOocytes)
                    .sum();
            this.averageViableOocytes = totalViableOocytes / oocyteCollections.size();
        } else {
            this.averageViableOocytes = 0.0;
        }
    }

    public void updateEmbryoPercentage() {
        if (oocyteCollections != null && !oocyteCollections.isEmpty()) {
            double totalPercentage = oocyteCollections.stream()
                    .mapToDouble(oc -> oc.getEmbryoProduction().getEmbryosPercentage())
                    .sum();
            this.averageEmbryoPercentage = totalPercentage / oocyteCollections.size();
        } else {
            this.averageEmbryoPercentage = 0.0;
        }
    }
}
