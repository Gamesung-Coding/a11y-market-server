package com.multicampus.gamesungcoding.a11ymarketserver.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(length = 36)
    private String userId;

    @Column(length = 30)
    private String userName;

    @Column(length = 100)
    private String userPass;

    @Column(length = 50)
    private String userEmail;

    @Column(length = 15)
    private String userPhone;

    @Column(length = 20)
    private String userNickname;

    @Column(length = 30)
    private String userRole;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // 회원 정보 수정 메소드
    public void updateUserInfo(String userName, String userEmail, String userPhone, String userNickname) {
        if (userName != null) {
            this.userName = userName;
        }
        if (userEmail != null) {
            this.userEmail = userEmail;
        }
        if (userPhone != null) {
            this.userPhone = userPhone;
        }
        if (userNickname != null) {
            this.userNickname = userNickname;
        }
    }

    // 비밀번호 변경 메소드
    public void updatePassword(String encodedPassword) {
        this.userPass = encodedPassword;
    }

}
