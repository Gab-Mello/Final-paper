package com.gabriel.pive.calendar.schedule.services;

import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.animals.services.BullService;
import com.gabriel.pive.calendar.schedule.dtos.ScheduleRequestDto;
import com.gabriel.pive.calendar.schedule.dtos.ScheduleResponseDto;
import com.gabriel.pive.calendar.schedule.entities.Schedule;
import com.gabriel.pive.calendar.schedule.enums.ProcedureStatus;
import com.gabriel.pive.calendar.schedule.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;


    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto){
        return ScheduleResponseDto.toScheduleResponseDto(scheduleRepository.save(dto.toSchedule()));
    }

    public Schedule getScheduleById(Long id){
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        if (optionalSchedule.isEmpty()){
            return null;
        }
        return optionalSchedule.get();
    }

    public ScheduleResponseDto editSchedule(Long id, ScheduleRequestDto dto){
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        if (optionalSchedule.isEmpty()){
            return null;
        }
        Schedule schedule = optionalSchedule.get();
        schedule.setProcedureType(dto.procedureType());
        schedule.setDate(dto.date());

        return ScheduleResponseDto.toScheduleResponseDto(schedule);
    }

    public ScheduleResponseDto cancelSchedule(Long id){
        if (getScheduleById(id) == null){
            return null;
        }
        Schedule schedule = getScheduleById(id);
        schedule.setProcedureStatus(ProcedureStatus.CANCELED);

        return ScheduleResponseDto.toScheduleResponseDto(scheduleRepository.save(schedule));
    }

    public List<ScheduleResponseDto> getSchedulesByDate(LocalDate date){
        List<Schedule> list = scheduleRepository.findByDateAndProcedureStatus(date, ProcedureStatus.PENDING);
        return ScheduleResponseDto.toScheduleReponseDtoList(list);
    }

    public List<ScheduleResponseDto> listAllSchedules(){
        List<Schedule> list = scheduleRepository.findByProcedureStatus(ProcedureStatus.PENDING);
        return ScheduleResponseDto.toScheduleReponseDtoList(list);
    }

}
