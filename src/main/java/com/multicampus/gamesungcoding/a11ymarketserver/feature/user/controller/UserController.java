package com.multicampus.gamesungcoding.a11ymarketserver.feature.user.controller;

import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.dto.UserDeleteRequest;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.dto.UserResponse;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.dto.UserUpdateRequest;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    // 회원 정보 조회
    @GetMapping("/v1/users/me")
    public ResponseEntity<UserResponse> getUserInfo(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UserResponse response = userService.getUserInfo(userDetails.getUsername());

        log.debug("User found: {}", response.userEmail());
        return ResponseEntity.ok(response);
    }

    // 회원 정보 수정
    @PatchMapping("/v1/users/me")
    public ResponseEntity<UserResponse> updateUserInfo(
            @AuthenticationPrincipal UserDetails userDetails,
            //@RequestParam String uuid,
            @Valid @RequestBody UserUpdateRequest request) {

        UserResponse response = userService.updateUserInfo(userDetails.getUsername(), request);

        log.debug("User updated: {}", response.userEmail());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/v1/users/me")
    public ResponseEntity<Void> deleteUser(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UserDeleteRequest req) {
        userService.deleteUser(userDetails.getUsername(), req);

        log.debug("User deleted: {}", userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
