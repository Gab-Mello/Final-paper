package com.gabriel.pive.calendar.controllers;

import com.gabriel.pive.calendar.dtos.ScheduleDto;
import com.gabriel.pive.calendar.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleDto> createSchedule(@RequestBody ScheduleDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDto> editSchedule(@PathVariable Long id, @RequestBody ScheduleDto dto){
        if (scheduleService.editSchedule(id, dto) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.editSchedule(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleDto> cancelSchedule(@PathVariable Long id){
        if (scheduleService.cancelSchedule(id) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.cancelSchedule(id));
    }
}
