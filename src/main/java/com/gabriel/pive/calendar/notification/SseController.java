package com.gabriel.pive.calendar.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class SseController {

    @Autowired
    private SseService sseService;

    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamEvents(){
        SseEmitter emitter = new SseEmitter();

        sseService.addEmitter(emitter);

        return emitter;
    }
}
