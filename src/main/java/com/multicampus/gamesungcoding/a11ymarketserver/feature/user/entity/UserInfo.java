package com.multicampus.gamesungcoding.a11ymarketserver.feature.user.entity;

public record UserInfo(String userEmail, String userNickname, UserRole userRole) {
    public static UserInfo fromEntity(Users user) {
        return new UserInfo(
                user.getUserEmail(),
                user.getUserNickname(),
                user.getUserRole()
        );
    }
}
