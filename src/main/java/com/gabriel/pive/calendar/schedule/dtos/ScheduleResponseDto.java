package com.gabriel.pive.calendar.schedule.dtos;

import com.gabriel.pive.calendar.schedule.entities.Schedule;
import com.gabriel.pive.calendar.schedule.enums.ProcedureStatus;
import com.gabriel.pive.calendar.schedule.enums.ProcedureType;

import java.time.LocalDate;

public record ScheduleResponseDto(ProcedureType procedureType,
                                  LocalDate date,
                                  ProcedureStatus procedureStatus) {

    public static ScheduleResponseDto toScheduleResponseDto(Schedule schedule){
        return new ScheduleResponseDto(schedule.getProcedureType(), schedule.getDate(), schedule.getProcedureStatus());
    }
}
