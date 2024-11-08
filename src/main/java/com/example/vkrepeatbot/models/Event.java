package com.example.vkrepeatbot.models;

public class Event {
    private String type;  // Тип события, например "message_new"
    private String event_id;  // Идентификатор события
    private String v;  // Версия API
    private Object object;  // Объект, инициировавший событие (например, сообщение)
    private long group_id;  // ID сообщества

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }
}