package com.example.vkrepeatbot.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
    private String type;  // Тип события, например "message_new"
    private String event_id;  // Идентификатор события
    private String v;  // Версия API
    private Object object;  // Объект, инициировавший событие (например, сообщение)
    private long group_id;  // ID сообщества

}