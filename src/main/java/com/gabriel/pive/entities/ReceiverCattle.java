package com.gabriel.pive.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

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

    private String name;

    private String breed;

    private LocalDateTime birth;

    public ReceiverCattle(String name, String breed, LocalDateTime birth){
        this.name = name;
        this.breed = breed;
        this.birth = birth;
    }
}
