package com.lsl.commons.config.boot;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ContextEvent {

    @EventListener
    public void ContextStartedEvent(ContextStartedEvent event) {
        System.out.println("ContextStartedEvent========================");
    }

    @EventListener
    public void listenerContextcloseEvent(ContextStoppedEvent event) {
        System.out.println("ContextClosedEvent========================");
    }
}
