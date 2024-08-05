package com.gabriel.pive.calendar.dtos;

import com.gabriel.pive.calendar.entities.Schedule;
import com.gabriel.pive.calendar.enums.ProcedureStatus;
import com.gabriel.pive.calendar.enums.ProcedureType;

import java.time.LocalDate;

public record ScheduleDto(ProcedureType procedureType,
                          LocalDate date,
                          ProcedureStatus procedureStatus) {

    public Schedule toSchedule(){
        return new Schedule(procedureType(), date(), ProcedureStatus.PENDING);
    }

    public static ScheduleDto toScheduleDto(Schedule schedule){
        return new ScheduleDto( schedule.getProcedureType(),
                                schedule.getDate(),
                                schedule.getProcedureStatus());
    }
}
