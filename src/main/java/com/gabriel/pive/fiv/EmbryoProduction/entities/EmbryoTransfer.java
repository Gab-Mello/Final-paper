package com.gabriel.pive.fiv.EmbryoProduction.entities;

import com.gabriel.pive.fiv.entities.Fiv;
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
@Table(name = "tb_transfers")
@Entity
public class EmbryoTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fiv_id")
    private Fiv fivTransfer;

    private LocalDate date;

    private String responsible;

    private String farm;

    @OneToMany(mappedBy = "transfer")
    private List<Embryo> embryos = new ArrayList<>();

    public EmbryoTransfer(Fiv fiv, LocalDate date, String responsible, String farm){
        this.fivTransfer = fiv;
        this.date = date;
        this.responsible = responsible;
        this.farm = farm;
    }

}
