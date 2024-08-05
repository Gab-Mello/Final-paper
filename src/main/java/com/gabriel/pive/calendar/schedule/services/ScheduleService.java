package com.gabriel.pive.calendar.schedule.services;

import com.gabriel.pive.calendar.schedule.dtos.ScheduleDto;
import com.gabriel.pive.calendar.schedule.entities.Schedule;
import com.gabriel.pive.calendar.schedule.enums.ProcedureStatus;
import com.gabriel.pive.calendar.schedule.repositories.ScheduleRepository;
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

    public Schedule getScheduleById(Long id){
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        if (optionalSchedule.isEmpty()){
            return null;
        }
        return optionalSchedule.get();
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

    public ScheduleDto cancelSchedule(Long id){
        if (getScheduleById(id) == null){
            return null;
        }
        Schedule schedule = getScheduleById(id);
        schedule.setProcedureStatus(ProcedureStatus.CANCELED);

        return ScheduleDto.toScheduleDto(scheduleRepository.save(schedule));
    }

}
