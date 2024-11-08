package com.example.vkrepeatbot.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Message {
    private int id;                // Идентификатор сообщения
    private long date;             // Время отправки в Unixtime
    private int peer_id;           // Идентификатор назначения
    private int from_id;           // Идентификатор отправителя
    private String text;           // Текст сообщения

}

