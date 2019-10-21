package com.example.chat.domain;

import java.util.UUID;

/**
 * Message.
 *
 * @author Alexey Voronin.
 * @since 21.10.2019.
 */
public class Message {

    private UUID userId;

    private String content;

    private String sender;

    private MessageType type;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public enum MessageType {
        CHAT, LEAVE, JOIN
    }
}
