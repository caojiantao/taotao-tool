package com.taotao.tool.event.upload;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class Event extends ApplicationEvent {

    @Getter
    private Integer fileId;

    public Event(Integer fileId) {
        super(fileId);
    }
}
