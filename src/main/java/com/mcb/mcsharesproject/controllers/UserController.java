package com.mcb.mcsharesproject.controllers;

import com.mcb.mcsharesproject.entities.user.User;
import com.mcb.mcsharesproject.services.user.UserService;
import com.mcb.mcsharesproject.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/users/save")
    public ResponseEntity<User> saveUser(@RequestBody UserVO userVO) {
        if (userService.getUser(userVO.getUsername()) != null) {
            return ResponseEntity.badRequest().build();
        }

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                                                        .path("/api/users/save")
                                                        .toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(userVO));
    }


}
