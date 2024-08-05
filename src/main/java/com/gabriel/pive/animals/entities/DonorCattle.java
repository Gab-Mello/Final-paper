package com.gabriel.pive.animals.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_donors")
@Entity
public class DonorCattle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String breed;

    private LocalDate birth;

    private String registrationNumber;


    public DonorCattle(String name, String breed, LocalDate birth, String registrationNumber){
        this.name = name;
        this.breed = breed;
        this.birth = birth;
        this.registrationNumber = registrationNumber;
    }
}
