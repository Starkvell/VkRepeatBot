package com.example.vkrepeatbot.services;

import com.example.vkrepeatbot.exceptions.FailedMessageException;
import com.example.vkrepeatbot.models.ClientInfo;
import com.example.vkrepeatbot.models.Event;
import com.example.vkrepeatbot.models.Message;
import com.example.vkrepeatbot.models.ObjectWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;


@Service
public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
    private final RestClient restClient = RestClient.create();
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${vk.api.token}")
    private String accessToken;

    @Value("${vk.api.version}")
    private String apiVersion;

    @Value("${vk.api.url}")
    private String apiUrl;

    public void handleMessageNew(Event event) {
        ObjectWrapper objectWrapper = convertToObjectWrapper(event);
        Message message;

        if (isOldVersion(event)) {
            message = extractMessageFromOldVersion(event);
        } else {
            message = extractMessageFromNewVersion(objectWrapper);
        }

        // Обрабатываем сообщение
        processMessage(message);
    }

    public void sendMessage(int userId, String message) {
        String url = apiUrl + "messages.send";
        // Устанавливаем параметры запроса
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("user_id", String.valueOf(userId));
        params.add("message", message);
        params.add("access_token", accessToken);
        params.add("v", apiVersion);
        params.add("random_id", String.valueOf(System.currentTimeMillis()));

        try {
            String responseBody = executePostRequest(url, params);
            handleResponse(responseBody);
            logger.info("Message sent successfully: {}", responseBody);
        } catch (FailedMessageException ex) {
            logger.error("Failed to send message due to VK API error: {}", ex.getMessage(), ex);
        } catch (Exception e) {
            logger.error("Unexpected error while sending message", e);
        }
    }

    private Message extractMessageFromOldVersion(Event event) {
        // Обрабатываем старую версию (без client_info)
        Message message = (Message) event.getObject();
        printMessageInfo(message);
        return message;
    }

    private Message extractMessageFromNewVersion(ObjectWrapper objectWrapper) {
        // Для версии 5.103 и выше
        Message message = objectWrapper.getMessage();
        ClientInfo clientInfo = objectWrapper.getClientInfo();

        printMessageInfo(message);
        printClientInfo(clientInfo);

        return message;
    }

    private String executePostRequest(String url, MultiValueMap<String, String> params) {
        ResponseEntity<String> response = restClient
                .post()
                .uri(url)
                .body(params)
                .retrieve()
                .toEntity(String.class);
        return response.getBody();
    }

    private void handleResponse(String responseBody) throws FailedMessageException, JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(responseBody);
        if (rootNode.has("error")) {
            JsonNode errorNode = rootNode.get("error");
            int errorCode = errorNode.get("error_code").asInt();
            String errorMsg = errorNode.get("error_msg").asText();
            throw new FailedMessageException(HttpStatus.BAD_REQUEST, errorCode, errorMsg);
        }
    }

    private boolean isOldVersion(Event event) {
        String version = event.getV();
        String minimumVersion = "5.103";

        // Разделяем строки версии по точке
        String[] currentVersionParts = version.split("\\.");
        String[] minimumVersionParts = minimumVersion.split("\\.");

        // Сравниваем части версий по порядку
        int length = Math.max(currentVersionParts.length, minimumVersionParts.length);
        for (int i = 0; i < length; i++) {
            // Если компоненты не существуют, то считаем их равными 0
            int currentPart = i < currentVersionParts.length ? Integer.parseInt(currentVersionParts[i]) : 0;
            int minimumPart = i < minimumVersionParts.length ? Integer.parseInt(minimumVersionParts[i]) : 0;

            if (currentPart < minimumPart) {
                return true;
            } else if (currentPart > minimumPart) {
                return false;
            }
        }

        return false;  // Версии одинаковые
    }

    private ObjectWrapper convertToObjectWrapper(Event event) {
        return objectMapper.convertValue(event.getObject(), ObjectWrapper.class);
    }

    private void printMessageInfo(Message message) {
        System.out.println("Получено новое сообщение: " + message.getText());
        System.out.println("От пользователя: " + message.getFrom_id());
    }

    private void printClientInfo(ClientInfo clientInfo) {
        System.out.println("Доступные действия: " + clientInfo.getButton_actions());
    }

    private void processMessage(Message message) {
        sendMessage(message.getFrom_id(), "Вы написали: " + message.getText());
    }


}
