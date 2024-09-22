package com.gabriel.pive.calendar.notification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Tag(name = "Notification", description = "Server send events (notification)")
@RestController
public class SseController {

    @Autowired
    private SseService sseService;

    @Operation(summary = "This endpoint make the connection to receive events from the server", description = "This endpoint make the connection to receive events from the server")
    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamEvents(){
        SseEmitter emitter = new SseEmitter();

        sseService.addEmitter(emitter);

        return emitter;
    }
}
