package com.example.vkrepeatbot.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientInfo {
    private List<String> button_actions;  // Доступные действия (например, кнопки)
    private boolean keyboard;  // Поддерживается ли клавиатура ботов клиентом
    private boolean inline_keyboard; // Поддерживается ли inline клавиатуры
    private boolean carousel; // Поддерживается ли карусель
    private int lang_id;  // Язык интерфейса

    public List<String> getButton_actions() {
        return button_actions;
    }

    public void setButton_actions(List<String> button_actions) {
        this.button_actions = button_actions;
    }

    public boolean isKeyboard() {
        return keyboard;
    }

    public void setKeyboard(boolean keyboard) {
        this.keyboard = keyboard;
    }

    public boolean isInline_keyboard() {
        return inline_keyboard;
    }

    public void setInline_keyboard(boolean inline_keyboard) {
        this.inline_keyboard = inline_keyboard;
    }

    public boolean isCarousel() {
        return carousel;
    }

    public void setCarousel(boolean carousel) {
        this.carousel = carousel;
    }

    public int getLang_id() {
        return lang_id;
    }

    public void setLang_id(int lang_id) {
        this.lang_id = lang_id;
    }
}

