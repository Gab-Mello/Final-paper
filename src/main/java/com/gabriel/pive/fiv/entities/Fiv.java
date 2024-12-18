package com.gabriel.pive.fiv.entities;

import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoTransfer;
import com.gabriel.pive.fiv.enums.FivStatusEnum;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;
import com.gabriel.pive.fiv.repositories.FivRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "fivTransfer")
    private List<EmbryoTransfer> embryoTransfer = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private FivStatusEnum status;

    private Integer totalOocytesCollected;

    private Integer totalViableOocytesCollected;

    private Integer totalEmbryos;

    private Float embryosPercentage;

    private Integer embryosRegistered;

    private Integer fivFrozenEmbryosNumber;

    private Integer fivDiscardedEmbryosNumber;

    private Integer fivTransferredEmbryosNumber;

    private Integer fivTotalPregnancy;

    private Float fivPregnancyPercentage;

    public Fiv(LocalDate date, String farm, String laboratory,
               String client, String veterinarian, String technical, String TE){
        this.date = date;
        this.farm = farm;
        this.laboratory = laboratory;
        this.client = client;
        this.veterinarian = veterinarian;
        this.technical = technical;
        this.TE = TE;
        this.totalOocytesCollected = 0;
        this.totalViableOocytesCollected = 0;
        this.totalEmbryos = 0;
        this.embryosRegistered = 0;
        this.fivTotalPregnancy = 0;
        this.fivTransferredEmbryosNumber = 0;
        this.fivDiscardedEmbryosNumber = 0;
        this.fivFrozenEmbryosNumber = 0;
    }

    public void updateTotalOocytesCollected(Integer oocytes){
        this.setTotalOocytesCollected(this.getTotalOocytesCollected() + oocytes);
    }
    public void updateTotalViableOocytesCollected(Integer oocytes){
        this.setTotalViableOocytesCollected(this.getTotalViableOocytesCollected() + oocytes);
    }
}
