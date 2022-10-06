package com.taotao.tool.event.upload;

import org.springframework.context.ApplicationEvent;

public class Event extends ApplicationEvent {

    public Event(Integer fileId) {
        super(fileId);
    }
}
