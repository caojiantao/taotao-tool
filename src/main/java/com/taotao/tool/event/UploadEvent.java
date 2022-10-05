package com.taotao.tool.event;

import org.springframework.context.ApplicationEvent;

public class UploadEvent extends ApplicationEvent {

    public UploadEvent(Integer fileId) {
        super(fileId);
    }
}
