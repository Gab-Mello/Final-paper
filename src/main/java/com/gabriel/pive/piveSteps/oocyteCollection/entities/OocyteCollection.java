package com.gabriel.pive.piveSteps.oocyteCollection.entities;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_oocyte_collections")
public class OocyteCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private DonorCattle donorCattle;

    @ManyToOne
    @JoinColumn(name = "bull_id")
    private Bull bull;

    private Integer totalOocytes;

    private Integer viableOocytes;

    private Integer nonViableOocytes;

    public OocyteCollection(DonorCattle donorCattle, Bull bull, Integer totalOocytes, LocalDate date){
        this.donorCattle = donorCattle;
        this.bull = bull;
        this.totalOocytes = totalOocytes;
        this.date = date;
    }


}
