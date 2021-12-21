package com.lab2.users.service;

import com.lab2.users.repo.UserRepo;
import com.lab2.users.repo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class UserService {

    private final UserRepo userRepo;

    public List<User> list() {
        final List<User> items = userRepo.findAll();
        return items;
    }

    public User read(long id) throws IllegalArgumentException {
        final Optional<User> item = userRepo.findById(id);
        if (item.isEmpty()) {
            throw new IllegalArgumentException("User unknown");
        }
        return item.get();
    }

    public User create(String login, String password, Boolean moderator, Integer status) {
        final User user = new User(login, password, moderator, status);
        final User item = userRepo.save(user);
        return item;
    }

    public User update(long id, String login, String password, Boolean moderator, Integer status) throws IllegalArgumentException {
        final Optional<User> item = userRepo.findById(id);
        if (item.isEmpty()) {
            throw new IllegalArgumentException("User unknown");
        }
        User user = item.get();
        if (login != null && !login.isBlank()) {
            user.setLogin(login);
        }
        if (password != null && !password.isBlank()) {
            user.setPassword(password);
            String passwordHash = password;
            user.setPasswordHash(passwordHash);
        }
        if (moderator != null) {
            user.setModerator(moderator);
        }
        if (status != null) {
            user.setStatus(status );
        }
        userRepo.save(user);
        return user;
    }

    public void remove(long id) {
        userRepo.deleteById(id);
    }
}
