package com.example.vkrepeatbot.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ClientInfo {
    private List<String> button_actions;  // Доступные действия (например, кнопки)
    private boolean keyboard;  // Поддерживается ли клавиатура ботов клиентом
    private boolean inline_keyboard; // Поддерживается ли inline клавиатуры
    private boolean carousel; // Поддерживается ли карусель
    private int lang_id;  // Язык интерфейса

}

