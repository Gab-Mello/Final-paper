package com.gabriel.pive.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_receivers")
@Entity
public class ReceiverCattle {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    public Integer registrationNumber;
    private String name;
    private String breed;
    private String embryo; //TODO: change to Embryo type

    public ReceiverCattle(String name, String breed, Integer registrationNumber){
        this.name = name;
        this.breed = breed;
        this.registrationNumber = registrationNumber;
    }
}
