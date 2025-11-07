package com.multicampus.gamesungcoding.a11ymarketserver.user.service;

import com.multicampus.gamesungcoding.a11ymarketserver.user.model.UserDTO;
import com.multicampus.gamesungcoding.a11ymarketserver.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO getUserInfo(String userId) {
        return userRepository.findById(userId)
                .map(UserDTO::fromEntity)
                .orElse(null);
    }

    @Override
    @Transactional
    public UserDTO updateUserInfo(String userId, UserDTO userDTO) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.updateUserInfo(
                            userDTO.getUserName(),
                            userDTO.getUserEmail(),
                            userDTO.getUserPhone(),
                            userDTO.getUserNickname()
                    );
                    return UserDTO.fromEntity(user);
                })
                .orElse(null);
    }

}
