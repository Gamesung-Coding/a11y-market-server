package com.multicampus.gamesungcoding.a11ymarketserver.user.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String userId;
    private String userName;
    private String userPass;
    private String userEmail;
    private String userPhone;
    private String userNickname;
    private String userRole;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserDTO fromEntity(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .userPhone(user.getUserPhone())
                .userNickname(user.getUserNickname())
                .userRole(user.getUserRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public void updateEntity(User user) {
        if (this.userName != null) {
            user.setUserName(this.userName);
        }
        if (this.userPass != null) {
            user.setUserPass(this.userPass);
        }
        if (this.userEmail != null) {
            user.setUserEmail(this.userEmail);
        }
        if (this.userPhone != null) {
            user.setUserPhone(this.userPhone);
        }
        if (this.userNickname != null) {
            user.setUserNickname(this.userNickname);
        }
    }
}
