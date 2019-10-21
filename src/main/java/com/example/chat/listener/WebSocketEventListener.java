package com.example.chat.listener;

import com.example.chat.domain.Message;
import com.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.UUID;

/**
 * WebSocketEventListener
 *
 * @author Alexey Voronin.
 * @since 21.10.2019.
 */
@Component
public class WebSocketEventListener {

    private final UserService userService;

    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public WebSocketEventListener(final SimpMessageSendingOperations messagingTemplate, final UserService userService) {
        this.messagingTemplate = messagingTemplate;
        this.userService = userService;
    }

    @EventListener
    public void handleWebSocketDisconnectListener(final SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        UUID id = (UUID) headerAccessor.getSessionAttributes().get("userId");
        if(username != null) {
            Message message = new Message();
            message.setType(Message.MessageType.LEAVE);
            message.setSender(username);
            this.userService.deleteUser(id);
            messagingTemplate.convertAndSend("/topic/public", message);
        }
    }
}
