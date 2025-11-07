package com.multicampus.gamesungcoding.a11ymarketserver.user.controller;

import com.multicampus.gamesungcoding.a11ymarketserver.user.model.UserDTO;
import com.multicampus.gamesungcoding.a11ymarketserver.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserInfo(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        UserDTO userDTO = userService.getUserInfo(userId);
        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping("/me")
    public ResponseEntity<UserDTO> updateUserInfo(
            HttpSession session,
            @RequestBody UserDTO userDTO) {
        String userId = (String) session.getAttribute("userId");
        UserDTO updateUser = userService.updateUserInfo(userId, userDTO);
        return ResponseEntity.ok(updateUser);
    }

}
