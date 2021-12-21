package com.lab2.users.api;

import com.lab2.users.api.dto.UserItem;
import com.lab2.users.repo.model.User;
import com.lab2.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public final class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> list() {
        List<User> items = userService.list();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> read(@PathVariable long id) {
        try {
            User item = userService.read(id);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserItem user) {
        try {
            String login = user.getLogin();
            String password = user.getPassword();
            Boolean moderator = user.getModerator();
            Integer status = user.getStatus();
            User item = userService.create(login, password, moderator, status);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable long id, @RequestBody UserItem user) {
        try {
            String login = user.getLogin();
            String password = user.getPassword();
            Boolean moderator = user.getModerator();
            Integer status = user.getStatus();
            User item = userService.update(id, login, password, moderator, status);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserItem> remove(@PathVariable long id) {
        userService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
