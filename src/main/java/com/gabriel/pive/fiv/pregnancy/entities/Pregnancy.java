package com.gabriel.pive.fiv.pregnancy.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.fiv.pregnancy.enums.PregnancyStatus;
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
@Table(name = "tb_pregnancy")
@Entity
public class Pregnancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "receiverCattleId")
    private ReceiverCattle pregReceiverCattle;

    private LocalDate transferDay;

    @Enumerated(EnumType.STRING)
    private PregnancyStatus status;

    private Integer gestationalAge;

    public Pregnancy(LocalDate transferDay, ReceiverCattle pregReceiverCattle, PregnancyStatus status){
        this.transferDay = transferDay;
        this.pregReceiverCattle = pregReceiverCattle;
        this.status = status;
        this.gestationalAge = 0;
    }

    }
