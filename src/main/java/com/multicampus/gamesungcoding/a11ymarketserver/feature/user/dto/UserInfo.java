package com.multicampus.gamesungcoding.a11ymarketserver.feature.user.dto;

import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.entity.UserRole;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.entity.Users;

public record UserInfo(String userEmail, String userNickname, UserRole userRole) {
    public static UserInfo fromEntity(Users user) {
        return new UserInfo(
                user.getUserEmail(),
                user.getUserNickname(),
                user.getUserRole()
        );
    }
}
