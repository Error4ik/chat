package com.example.chat.service;

import com.example.chat.domain.User;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * UserService.
 *
 * @author Alexey Voronin.
 * @since 21.10.2019.
 */
@Service
public class UserService {
    private final Map<UUID, User> userMap = new HashMap<>();

    public void addUser(final User user) {
        this.userMap.put(user.getId(), user);
    }

    public void deleteUser(final UUID userId) {
        this.userMap.remove(userId);
    }

    public List<User> getUsers() {
        return new ArrayList<>(this.userMap.values());
    }
}
