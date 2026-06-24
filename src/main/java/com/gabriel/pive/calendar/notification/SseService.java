package com.gabriel.pive.calendar.notification;

import com.gabriel.pive.calendar.schedule.entities.Schedule;
import com.gabriel.pive.calendar.schedule.enums.ProcedureStatus;
import com.gabriel.pive.calendar.schedule.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SseService {

    private static final Logger log = LoggerFactory.getLogger(SseService.class);

    /**
     * Thread-safe holder for active SSE connections. {@link CopyOnWriteArrayList}
     * is the right collection here because:
     *   - writes ({@link #addEmitter}, {@code onCompletion}/{@code onTimeout}
     *     callbacks) are rare relative to iterations;
     *   - iterations ({@link #sendNotification}) happen from a scheduled
     *     thread while HTTP request threads can be mutating the list;
     *   - the snapshot semantics of {@code CopyOnWriteArrayList.iterator()}
     *     mean no {@code ConcurrentModificationException} can occur during
     *     iteration even if elements are added or removed concurrently.
     */
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    private final ScheduleRepository scheduleRepository;

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
                        log.debug("Sent SSE notification for schedule id={} type={}",
                                notification.getId(), notification.getProcedureType());
                        notification.setProcedureStatus(ProcedureStatus.SUCCESS);
                        scheduleRepository.save(notification);
                    }catch (IOException e){
                        log.warn("Dropping unreachable SSE emitter while sending schedule id={}: {}",
                                notification.getId(), e.getMessage());
                        emitters.remove(emitter);
                    }
            }

        }
    }
}
