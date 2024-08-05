package com.gabriel.pive.calendar.services;

import com.gabriel.pive.calendar.dtos.ScheduleDto;
import com.gabriel.pive.calendar.entities.Schedule;
import com.gabriel.pive.calendar.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public ScheduleDto createSchedule(ScheduleDto dto){
        return ScheduleDto.toScheduleDto(scheduleRepository.save(dto.toSchedule()));
    }

    public ScheduleDto editSchedule(Long id, ScheduleDto dto){
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        if (optionalSchedule.isEmpty()){
            return null;
        }
        Schedule schedule = optionalSchedule.get();
        schedule.setProcedureType(dto.procedureType());
        schedule.setDate(dto.date());

        return ScheduleDto.toScheduleDto(schedule);
    }

}
