package com.gabriel.pive.fiv.EmbryoProduction.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.fiv.EmbryoProduction.enums.EmbryoDestiny;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_embryos")
@Entity
public class Embryo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "production_id")
    private EmbryoProduction embryoEmbryoProduction;

    @ManyToOne
    @JoinColumn(name = "bull_id")
    private Bull embryoBull;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private DonorCattle embryoDonorCattle;

    @Enumerated(EnumType.STRING)
    private EmbryoDestiny destiny;

    @OneToOne
    @JoinColumn(name = "receiver_id")
    private ReceiverCattle embryoReceiverCattle;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "transfer_id")
    private EmbryoTransfer transfer;

    public Embryo(EmbryoProduction production, EmbryoTransfer transfer,
                  ReceiverCattle receiverCattle, DonorCattle donorCattle, Bull bull, EmbryoDestiny destiny){
        this.embryoEmbryoProduction = production;
        this.transfer = transfer;
        this.embryoReceiverCattle = receiverCattle;
        this.embryoDonorCattle = donorCattle;
        this.embryoBull = bull;
        this.destiny = destiny;
    }

    public Embryo(EmbryoProduction production, DonorCattle donorCattle, Bull bull, EmbryoDestiny destiny){
        this.embryoEmbryoProduction = production;
        this.embryoDonorCattle = donorCattle;
        this.embryoBull = bull;
        this.destiny = destiny;
    }


}
