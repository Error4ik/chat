package com.example.chat.controller;

import com.example.chat.domain.Message;
import com.example.chat.domain.User;
import com.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Chat controller.
 *
 * @author Alexey Voronin.
 * @since 21.10.2019.
 */
@RestController
public class ChatController {

    private final UserService userService;

    @Autowired
    public ChatController(final UserService userService) {
        this.userService = userService;
    }

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public Message register(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("userId", message.getUserId());
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        this.userService.addUser(new User(message.getUserId(), message.getSender()));
        return message;
    }

    @MessageMapping("chat.send")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message message) {
        return message;
    }

    @RequestMapping("/users")
    public List<User> getUserOnline() {
        return this.userService.getUsers();
    }
}
