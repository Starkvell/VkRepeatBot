package com.example.vkrepeatbot.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private int id;                // Идентификатор сообщения
    private long date;             // Время отправки в Unixtime
    private int peer_id;           // Идентификатор назначения
    private int from_id;           // Идентификатор отправителя
    private String text;           // Текст сообщения


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getPeer_id() {
        return peer_id;
    }

    public void setPeer_id(int peer_id) {
        this.peer_id = peer_id;
    }

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

