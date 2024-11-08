package com.example.vkrepeatbot.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ObjectWrapper {
    private Message message;
    @JsonProperty("client_info")
    private ClientInfo clientInfo;
}
