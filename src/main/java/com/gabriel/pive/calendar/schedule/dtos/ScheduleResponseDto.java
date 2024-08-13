package com.gabriel.pive.calendar.schedule.dtos;

import com.gabriel.pive.calendar.schedule.entities.Schedule;
import com.gabriel.pive.calendar.schedule.enums.ProcedureStatus;
import com.gabriel.pive.calendar.schedule.enums.ProcedureType;

import java.time.LocalDate;
import java.util.List;

public record ScheduleResponseDto(Long id,
                                  ProcedureType procedureType,
                                  LocalDate date,
                                  ProcedureStatus procedureStatus) {

    public static ScheduleResponseDto toScheduleResponseDto(Schedule schedule){
        return new ScheduleResponseDto(schedule.getId(), schedule.getProcedureType(), schedule.getDate(), schedule.getProcedureStatus());
    }

    public static List<ScheduleResponseDto> toScheduleReponseDtoList(List<Schedule> list){
        return list.stream().map(schedule -> ScheduleResponseDto.toScheduleResponseDto(schedule)).toList();
    }

}
