package com.gabriel.pive.fiv.EmbryoProduction.entities;

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

    public Embryo(EmbryoProduction embryoProduction, EmbryoDestiny destiny, ReceiverCattle receiverCattle){
        this.embryoEmbryoProduction = embryoProduction;
        this.destiny = destiny;
        this.embryoReceiverCattle = receiverCattle;
    }



}
