package com.gabriel.pive.fiv.cultivation.entities;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.entities.ReceiverCattle;
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
    @JoinColumn(name = "cultivation_id")
    private Cultivation embryoCultivation;

    @ManyToOne
    @JoinColumn(name = "bull_id")
    private Bull embryoBull;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private DonorCattle embryoDonorCattle;

    private boolean frozen = false;

    @OneToOne
    @JoinColumn(name = "receiver_id")
    private ReceiverCattle embryoReceiverCattle;

    public Embryo(boolean frozen, ReceiverCattle receiverCattle){
        this.frozen = frozen;
        this.embryoReceiverCattle = receiverCattle;
    }



}
