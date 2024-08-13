package com.gabriel.pive.calendar.schedule.controllers;

import com.gabriel.pive.calendar.schedule.dtos.ScheduleRequestDto;
import com.gabriel.pive.calendar.schedule.dtos.ScheduleResponseDto;
import com.gabriel.pive.calendar.schedule.services.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schedule")
@Tag(name = "Schedule", description = "calendar management")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Operation(summary = "Save a new schedule", description = "It saves and returns a json with the new schedule")
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(dto));
    }

    @Operation(summary = "Edit a schedule by id", description = "It edits the schedule's data")
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> editSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto dto){
        if (scheduleService.editSchedule(id, dto) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.editSchedule(id, dto));
    }

    @Operation(summary = "Cancel a schedule by Id", description = "It cancels the schedule ")
    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> cancelSchedule(@PathVariable Long id){
        if (scheduleService.cancelSchedule(id) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.cancelSchedule(id));
    }
}
