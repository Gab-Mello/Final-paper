package com.gabriel.pive.fiv.oocyteCollection.entities;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.fiv.entities.Fiv;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_oocyte_collections")
@ToString
public class OocyteCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "oocyteCollection")
    private Fiv fiv;

    private LocalDate date;

    private String farm;

    private String laboratory;

    private String client;

    private String veterinarian;

    private String technical;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private DonorCattle donorCattle;

    @ManyToOne
    @JoinColumn(name = "bull_id")
    private Bull bull;

    private Integer totalOocytes;

    private Integer viableOocytes;

    private Integer nonViableOocytes;


    public OocyteCollection(Fiv fiv, DonorCattle donorCattle, Bull bull, String farm, String laboratory, String client,
                            String veterinarian, String technical,Integer totalOocytes, Integer viableOocytes,
                            Integer nonViableOocytes, LocalDate date){
        this.fiv = fiv;
        this.date = date;
        this.farm = farm;
        this.laboratory = laboratory;
        this.client = client;
        this.veterinarian = veterinarian;
        this.technical = technical;
        this.donorCattle = donorCattle;
        this.bull = bull;
        this.totalOocytes = totalOocytes;
        this.viableOocytes = viableOocytes;
        this.nonViableOocytes = nonViableOocytes;
    }


}
