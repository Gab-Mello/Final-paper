package com.gabriel.pive.calendar.schedule.repositories;

import com.gabriel.pive.calendar.schedule.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
