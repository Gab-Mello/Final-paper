package com.gabriel.pive.calendar.schedule.entities;

import com.gabriel.pive.calendar.schedule.enums.ProcedureStatus;
import com.gabriel.pive.calendar.schedule.enums.ProcedureType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
// NOTE: every other table in the project uses the `tb_` prefix (tb_bulls,
// tb_donors, tb_fivs, tb_oocyte_collections, ...). This one is the odd
// one out -- a "db_" prefix from an earlier iteration. The rename to
// `tb_schedules` is DEFERRED until Flyway is adopted: changing the
// @Table value here while the live schema still says `db_schedules`
// would make ddl-auto=validate fail-start, and ddl-auto=update would
// leave both tables behind. Once a V1__baseline.sql captures the
// current schema, a follow-up Flyway migration can ALTER TABLE
// db_schedules RENAME TO tb_schedules and this annotation will catch
// up in the same commit.
@Table(name = "db_schedules")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public String toString() {
        return "Schedule" +
                "id=" + id
                ;
    }
}
