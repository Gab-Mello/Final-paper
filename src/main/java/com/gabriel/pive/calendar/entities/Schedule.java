package com.gabriel.pive.calendar.entities;

import com.gabriel.pive.calendar.enums.ProcedureStatus;
import com.gabriel.pive.calendar.enums.ProcedureType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "db_schedules")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProcedureType procedureType;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private ProcedureStatus procedureStatus;

    public Schedule(ProcedureType procedureType, LocalDate date,ProcedureStatus procedureStatus){
        this.procedureType = procedureType;
        this.date = date;
        this.procedureStatus = procedureStatus;
    }

}
