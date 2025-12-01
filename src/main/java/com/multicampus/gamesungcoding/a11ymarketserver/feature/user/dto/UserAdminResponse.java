package com.multicampus.gamesungcoding.a11ymarketserver.feature.user.dto;

import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.entity.UserRole;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.entity.Users;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record UserAdminResponse(
        UUID userId,
        String userName,
        String userEmail,
        String userNickname,
        UserRole userRole,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime createdAt,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime updatedAt) {

    // 향후 User 클래스로 위임 예정
    // 테스트를 위해 DTO에 추가
    public static UserAdminResponse fromEntity(Users user) {
        // 이름을 홍*동 형태로 마스킹
        String maskedName = user.getUserName();
        for (int i = 1; i < maskedName.length() - 1; i++) {
            maskedName = maskedName.replace(maskedName.charAt(i), '*');
        }

        return UserAdminResponse.builder()
                .userId(user.getUserId())
                .userName(maskedName)
                .userEmail(user.getUserEmail())
                .userNickname(user.getUserNickname())
                .userRole(user.getUserRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
