package com.multicampus.gamesungcoding.a11ymarketserver.user.service;

import com.multicampus.gamesungcoding.a11ymarketserver.user.model.UserDTO;

public interface UserService {
    // 마이페이지 - 회원 정보 조회
    UserDTO getUserInfo(String userId);

    // 마이페이지 - 회원 정보 수정
    UserDTO updateUserInfo(String userId, UserDTO userDTO);
}
