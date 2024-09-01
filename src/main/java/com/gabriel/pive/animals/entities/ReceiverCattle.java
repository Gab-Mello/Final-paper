package com.gabriel.pive.animals.entities;

import com.gabriel.pive.fiv.cultivation.entities.Embryo;
import com.gabriel.pive.fiv.pregnancy.entities.Pregnancy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_receivers")
@Entity
public class ReceiverCattle {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String registrationNumber;

    private String name;

    private String breed;

    @OneToOne(mappedBy = "embryoReceiverCattle")
    private Embryo embryo;

    @OneToOne(mappedBy = "pregReceiverCattle")
    private Pregnancy pregnancy;

    public ReceiverCattle(String name, String breed, String registrationNumber){
        this.name = name;
        this.breed = breed;
        this.registrationNumber = registrationNumber;
    }
}
