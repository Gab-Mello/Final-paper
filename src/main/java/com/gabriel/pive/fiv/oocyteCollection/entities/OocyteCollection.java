package com.gabriel.pive.fiv.oocyteCollection.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "oocyteCollection_id")
    @ManyToOne
    private Fiv fiv;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private DonorCattle donorCattle;

    @ManyToOne
    @JoinColumn(name = "bull_id")
    private Bull bull;

    private Integer totalOocytes;

    private Integer viableOocytes;

    public OocyteCollection(Fiv fiv, DonorCattle donorCattle, Bull bull, Integer totalOocytes, Integer viableOocytes){
        this.fiv = fiv;
        this.donorCattle = donorCattle;
        this.bull = bull;
        this.totalOocytes = totalOocytes;
        this.viableOocytes = viableOocytes;
    }


}
