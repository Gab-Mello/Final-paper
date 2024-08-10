package com.gabriel.pive.calendar.notification;

import com.gabriel.pive.calendar.schedule.entities.Schedule;
import com.gabriel.pive.calendar.schedule.enums.ProcedureStatus;
import com.gabriel.pive.calendar.schedule.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SseService {

    private final List<SseEmitter> emitters = new ArrayList<>();

    @Autowired
    private ScheduleRepository scheduleRepository;

    public void addEmitter(SseEmitter emitter){
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
    }

    @Scheduled(fixedDelay = 24, timeUnit = TimeUnit.HOURS)
    public void sendNotification(){

        List<Schedule> pendingNotifications = scheduleRepository.findByDateAndProcedureStatus(LocalDate.now(), ProcedureStatus.PENDING);

        for (Schedule notification : pendingNotifications){
            for (SseEmitter emitter : emitters){
                    try{
                        emitter.send(SseEmitter.event().name("notification").data(notification.getProcedureType()));
                        System.out.println(notification);
                        notification.setProcedureStatus(ProcedureStatus.SUCCESS);
                        scheduleRepository.save(notification);
                    }catch (IOException e){
                        emitters.remove(emitter);
                    }
            }

        }
    }
}
