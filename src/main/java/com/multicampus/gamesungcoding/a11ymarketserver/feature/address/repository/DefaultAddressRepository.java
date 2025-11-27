package com.multicampus.gamesungcoding.a11ymarketserver.feature.address.repository;

import com.multicampus.gamesungcoding.a11ymarketserver.feature.address.model.DefaultAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface DefaultAddressRepository extends JpaRepository<DefaultAddress, UUID> {
    // 기본 배송지 조회
    Optional<DefaultAddress> findByUserId(UUID userId);

    @Query("""
            SELECT da
            FROM DefaultAddress da
            WHERE da.userId = (
                    SELECT u.userId
                    FROM Users u
                    WHERE u.userEmail = :userEmail
                 )
            """)
    DefaultAddress findByUserEmail(@Param("userEmail") String userEmail);
}
