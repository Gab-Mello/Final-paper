package com.gabriel.pive.calendar.schedule.repositories;

import com.gabriel.pive.calendar.schedule.entities.Schedule;
import com.gabriel.pive.calendar.schedule.enums.ProcedureStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    List<Schedule> findByDateAndProcedureStatus(LocalDate date, ProcedureStatus status);

    List<Schedule> findByProcedureStatus(ProcedureStatus status);
}
