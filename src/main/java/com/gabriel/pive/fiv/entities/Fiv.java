package com.gabriel.pive.fiv.entities;

import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.enums.FivStatusEnum;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_fivs")
@Entity
public class Fiv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String farm;

    private String laboratory;

    private String client;

    private String veterinarian;

    private String technical;

    private String TE;

    @OneToMany(mappedBy = "fiv")
    private List<OocyteCollection> oocyteCollections;

    @OneToOne
    @JoinColumn(name = "production_id")
    private EmbryoProduction embryoProduction;

    @Enumerated(EnumType.STRING)
    private FivStatusEnum status;

    public Fiv(LocalDate date, String farm, String laboratory,
               String client, String veterinarian, String technical, String TE){
        this.date = date;
        this.farm = farm;
        this.laboratory = laboratory;
        this.client = client;
        this.veterinarian = veterinarian;
        this.technical = technical;
        this.TE = TE;
    }
}
