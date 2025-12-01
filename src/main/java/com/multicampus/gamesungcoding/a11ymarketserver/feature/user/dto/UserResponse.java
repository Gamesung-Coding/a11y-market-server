package com.multicampus.gamesungcoding.a11ymarketserver.feature.user.dto;

import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.entity.UserRole;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.entity.Users;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(UUID userId,
                           String userName,
                           String userEmail,
                           String userPhone,
                           String userNickname,
                           UserRole userRole,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt) {

    public static UserResponse fromEntity(Users user) {
        return new UserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserPhone(),
                user.getUserNickname(),
                user.getUserRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

}
