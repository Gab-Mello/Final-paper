package com.gabriel.pive.calendar.notification;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SseService {
    private final List<SseEmitter> emitters = new ArrayList<>();

    public void addEmitter(SseEmitter emitter){
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void sendNotification(){
        for (SseEmitter emitter : emitters){
            try{
                emitter.send(SseEmitter.event().name("event-name").data(emitter));
            }catch (IOException e){
                emitters.remove(emitter);
            }
        }
    }
}
