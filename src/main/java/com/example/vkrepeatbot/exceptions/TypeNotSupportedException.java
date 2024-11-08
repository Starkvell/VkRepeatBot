package com.example.vkrepeatbot.exceptions;

import com.example.vkrepeatbot.models.Event;
import lombok.Getter;

@Getter
public class TypeNotSupportedException extends RuntimeException {

    private final Event event;
    private final String type;

    public TypeNotSupportedException(Event event) {
        super("Type not supported: " + event.getType());
        this.event = event;
        this.type = event.getType();
    }

}
