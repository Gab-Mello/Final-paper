package com.gabriel.pive.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_bulls")
@Entity
public class Bull {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer registrationNumber;

    public Bull(String name, Integer registrationNumber){
        this.name = name;
        this.registrationNumber = registrationNumber;
    }
}
