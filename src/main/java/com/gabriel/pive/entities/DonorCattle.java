package com.gabriel.pive.entities;

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

    private Integer registrationNumber;


    public DonorCattle(String name, String breed, Integer registrationNumber, LocalDate birth){
        this.name = name;
        this.breed = breed;
        this.registrationNumber = registrationNumber;
        this.birth = birth;
    }
}
