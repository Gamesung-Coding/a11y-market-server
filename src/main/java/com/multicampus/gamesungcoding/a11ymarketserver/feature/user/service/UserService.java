package com.multicampus.gamesungcoding.a11ymarketserver.feature.user.service;

import com.multicampus.gamesungcoding.a11ymarketserver.common.exception.InvalidRequestException;
import com.multicampus.gamesungcoding.a11ymarketserver.common.exception.UserNotFoundException;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.auth.service.AuthService;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.dto.UserDeleteRequest;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.dto.UserResponse;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.dto.UserUpdateRequest;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthService authService;

    // 마이페이지 - 회원 정보 조회
    public UserResponse getUserInfo(String userEmail) {
        return userRepository.findByUserEmail(userEmail)
                .map(UserResponse::fromEntity)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 존재하지 않습니다."));
    }

    // 마이페이지 - 회원 정보 수정
    @Transactional
    public UserResponse updateUserInfo(String userEmail, UserUpdateRequest dto) {
        return userRepository.findByUserEmail(userEmail)
                .map(user -> {
                    user.updateUserInfo(dto);
                    return user;
                })
                .map(UserResponse::fromEntity)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 존재하지 않습니다."));
    }

    public void deleteUser(String userEmail, UserDeleteRequest req) {
        var user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 존재하지 않습니다."));

        if (!passwordEncoder.matches(req.userPassword(), user.getUserPass())) {
            throw new InvalidRequestException("비밀번호가 일치하지 않습니다.");
        }


        // authService.logout(userEmail);

        userRepository.delete(user);
    }
}
