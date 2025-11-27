package com.multicampus.gamesungcoding.a11ymarketserver.feature.address.repository;

import com.multicampus.gamesungcoding.a11ymarketserver.feature.address.model.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Addresses, UUID> {
    // 전체 배송지 조회 (최신순)
    List<Addresses> findByUserIdOrderByCreatedAtDesc(UUID userId);

    // 사용자 특정 배송지 조회
    Optional<Addresses> findByAddressIdAndUserId(UUID addressId, UUID userId);

    // 사용자 이메일로 배송지 조회
    @Query("""
             SELECT a
             FROM Addresses a
             WHERE a.userId = (
                    SELECT u.userId
                    FROM Users u
                    WHERE u.userEmail = :userEmail
                 )
            """)
    List<Addresses> findByUserEmail(String userEmail);
}
