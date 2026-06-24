package com.gabriel.pive.calendar.schedule.dtos;

import com.gabriel.pive.calendar.schedule.entities.Schedule;
import com.gabriel.pive.calendar.schedule.enums.ProcedureStatus;
import com.gabriel.pive.calendar.schedule.enums.ProcedureType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ScheduleRequestDto(@NotNull(message = "Tipo de procedimento em branco.") ProcedureType procedureType,
                                 @NotNull(message = "Data em branco.")
                                 @FutureOrPresent(message = "Data não pode ser no passado.")
                                 LocalDate date) {



    public Schedule toSchedule(){
        return new Schedule(procedureType(), date(), ProcedureStatus.PENDING);
    }


}
