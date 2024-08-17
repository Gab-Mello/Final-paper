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

    private Bull bull;

    private DonorCattle donorCattle;

    private boolean frozen = false;

    private ReceiverCattle receiverCattle;

    @ManyToOne
    @JoinColumn(name = "cultivation_id")
    private Cultivation cultivation;




}
