package com.example.vkrepeatbot.controllers;

import com.example.vkrepeatbot.exceptions.TypeNotSupportedException;
import com.example.vkrepeatbot.models.Event;
import com.example.vkrepeatbot.services.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/callback")
public class VkCallbackController {
    private final MessageService messageService;

    @Value("${vk.confirmation-code}")
    private String confirmationCode;

    @Autowired
    public VkCallbackController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public String handleCallback(@RequestBody Event event) {
        String type = event.getType();

        if (type.equals("confirmation")) {
            return confirmationCode;
        } else if (type.equals("message_new")) {
            messageService.handleMessageNew(event);
            return "ok";
        } else {
            throw new TypeNotSupportedException(event);
        }
    }

    @ExceptionHandler(TypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleTypeNotSupportedException(TypeNotSupportedException ex) {
        return "Unsupported event type: " + ex.getType();
    }
}