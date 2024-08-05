package com.gabriel.pive.calendar.services;

import com.gabriel.pive.calendar.dtos.ScheduleDto;
import com.gabriel.pive.calendar.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public ScheduleDto createSchedule(ScheduleDto dto){
        return ScheduleDto.toScheduleDto(scheduleRepository.save(dto.toSchedule()));
    }

}
