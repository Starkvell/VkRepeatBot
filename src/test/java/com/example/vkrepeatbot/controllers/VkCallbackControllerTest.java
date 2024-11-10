package com.example.vkrepeatbot.controllers;

import com.example.vkrepeatbot.exceptions.TypeNotSupportedException;
import com.example.vkrepeatbot.models.Event;
import com.example.vkrepeatbot.services.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class VkCallbackControllerTest {

    @MockBean
    private MessageService messageService;

    @Autowired
    private VkCallbackController vkCallbackController;

    @Value("${vk.confirmation-code}")
    private String confirmationCode;


    @Test
    void testHandleCallbackConfirmation() {
        Event event = new Event();
        event.setType("confirmation");

        String response = vkCallbackController.handleCallback(event);

        assertEquals(confirmationCode, response);
    }

    @Test
    public void testHandleCallbackMessageNew() {
        Event event = new Event();
        event.setType("message_new");

        String response = vkCallbackController.handleCallback(event);

        assertEquals("ok", response);
        verify(messageService, times(1)).handleMessageNew(event);
    }

    @Test
    public void testHandleCallbackUnsupportedType() {
        Event event = new Event();
        event.setType("unsupported_type");

        try {
            vkCallbackController.handleCallback(event);
        } catch (TypeNotSupportedException ex) {
            String response = vkCallbackController.handleTypeNotSupportedException(ex);
            assertEquals("Unsupported event type: " + event.getType(), response);
        }
    }

}