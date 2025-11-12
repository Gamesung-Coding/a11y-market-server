package com.multicampus.gamesungcoding.a11ymarketserver.auth.controller;

import com.multicampus.gamesungcoding.a11ymarketserver.auth.dto.*;
import com.multicampus.gamesungcoding.a11ymarketserver.auth.service.AuthService;
import com.multicampus.gamesungcoding.a11ymarketserver.user.model.Users;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO dto, HttpSession session) {
        Users user = authService.login(dto);
        // 로그인 성공
        if (user != null) {
            session.setAttribute("loginUser", user);

            var userResp = UserRespDTO.builder()
                    .userId(user.getUserId())
                    .userName(user.getUserName())
                    .userEmail(user.getUserEmail())
                    .userNickname(user.getUserNickname())
                    .userRole(user.getUserRole())
                    .build();

            return ResponseEntity.ok(userResp); // 200 ok와 JSON 반환
        } else {
            var errBody = LoginErrResponse.builder()
                    .message("이메일 또는 비밀번호가 올바르지 않습니다.")
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errBody);
        }
    }

    @PostMapping("/auth/logout")
    @ResponseBody
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "로그아웃 성공";
    }

    @PostMapping("/auth/join")
    public ResponseEntity<?> join(@RequestBody @Valid JoinRequestDTO dto) {

        Users savedUser = authService.join(dto);

        // 이메일 중복 시
        if (savedUser == null) {
            ErrorResponseDTO error = ErrorResponseDTO.builder()
                    .message("이미 존재하는 이메일입니다.")
                    .build();

            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);


        }

        // 회원가입 성공 시
        JoinResponseDTO resp = JoinResponseDTO.builder()
                .msg("회원가입 성공")
                .user(
                        UserRespDTO.builder()
                                .userId(savedUser.getUserId())
                                .userName(savedUser.getUserName())
                                .userEmail(savedUser.getUserEmail())
                                .userNickname(savedUser.getUserNickname())
                                .userRole(savedUser.getUserRole())
                                .build()
                ).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

}

