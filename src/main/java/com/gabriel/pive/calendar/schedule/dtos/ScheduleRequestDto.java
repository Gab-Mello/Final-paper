package com.gabriel.pive.calendar.schedule.dtos;

import com.gabriel.pive.calendar.schedule.entities.Schedule;
import com.gabriel.pive.calendar.schedule.enums.ProcedureStatus;
import com.gabriel.pive.calendar.schedule.enums.ProcedureType;

import java.time.LocalDate;

public record ScheduleRequestDto(ProcedureType procedureType,
                                 LocalDate date) {



    public Schedule toSchedule(){
        return new Schedule(procedureType(), date(), ProcedureStatus.PENDING);
    }


}
