package com.multicampus.gamesungcoding.a11ymarketserver.feature.user.repository;

import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.entity.UserA11yProfile;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserA11yProfileRepository extends JpaRepository<UserA11yProfile, UUID> {

    // 특정 사용자 접근성 프로필 목록 조회
    List<UserA11yProfile> findAllByUserOrderByCreatedAtAsc(Users user);

    Optional<UserA11yProfile> findByProfileIdAndUser(UUID profileId, Users user);

    // 프로필 삭제
    Long deleteByProfileIdAndUser(UUID profileId, Users user);

    // 사용자별 프로필 이름 중복 체크
    boolean existsByUserAndProfileInfo_ProfileName(Users user, String profileName);

}
