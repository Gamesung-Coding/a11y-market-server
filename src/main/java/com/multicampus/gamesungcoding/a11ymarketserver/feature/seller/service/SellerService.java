package com.multicampus.gamesungcoding.a11ymarketserver.feature.seller.service;

import com.multicampus.gamesungcoding.a11ymarketserver.common.exception.DataDuplicatedException;
import com.multicampus.gamesungcoding.a11ymarketserver.common.exception.UserNotFoundException;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.product.model.Product;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.product.model.ProductDTO;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.product.model.ProductStatus;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.product.repository.ProductRepository;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.seller.model.*;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.seller.repository.SellerRepository;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.model.Users;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * 판매자 비즈니스 로직 구현체
 */
@Service
@RequiredArgsConstructor
@Transactional
public class SellerService {

    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public SellerApplyResponse applySeller(String userEmail, SellerApplyRequest request) {
        Users user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 존재하지 않습니다."));

        // 이미 판매자 신청 또는 등록 이력이 있는지 체크
        sellerRepository.findByUserId(user.getUserId()).ifPresent(existing -> {
            throw new DataDuplicatedException("이미 판매자이거나 판매자 신청 이력이 존재합니다.");
        });

        // 신규 Seller 엔티티 생성
        Seller seller = Seller.builder()
                .userId(user.getUserId())
                .sellerName(request.getSellerName())
                .businessNumber(request.getBusinessNumber())
                .sellerGrade(SellerGrades.NEWER.getGrade())
                .sellerIntro(request.getSellerIntro())
                .a11yGuarantee(false)
                .sellerSubmitStatus(SellerSubmitStatus.PENDING.getStatus())
                .build();

        Seller saved = sellerRepository.save(seller);

        return SellerApplyResponse.builder()
                .sellerId(saved.getSellerId())
                .sellerName(saved.getSellerName())
                .businessNumber(saved.getBusinessNumber())
                .sellerGrade(saved.getSellerGrade())
                .sellerIntro(saved.getSellerIntro())
                .a11yGuarantee(saved.getA11yGuarantee())
                .sellerSubmitStatus(saved.getSellerSubmitStatus())
                .submitDate(saved.getSubmitDate())
                .approvedDate(saved.getApprovedDate())
                .build();
    }

    public ProductDTO registerProduct(UUID userId, SellerProductRegisterRequest request) {

        // 1) userId 로 판매자 조회
        Seller seller = sellerRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalStateException("판매자 정보가 존재하지 않습니다. 먼저 판매자 가입 신청을 완료하세요."));

        // 판매자 승인 여부 확인
        if (!seller.getSellerSubmitStatus().equals(SellerSubmitStatus.PENDING.getStatus())) {
            throw new IllegalStateException("판매자 승인 완료 후 상품 등록이 가능합니다.");
        }

        // 2) Product 엔티티 생성
        UUID sellerId = UUID.fromString(request.getSellerId());
        UUID categoryId = UUID.fromString(request.getCategoryId());

        Product product = Product.builder()
                .sellerId(sellerId)
                .categoryId(categoryId)
                .productName(request.getProductName())
                .productDescription(request.getProductDescription())
                .productPrice(request.getProductPrice())
                .productStock(request.getProductStock())
                // 관리자 승인 대기 상태
                .productStatus(ProductStatus.PENDING.getStatus())
                .build();

        // 3) 저장
        Product saved = productRepository.save(product);

        // 4) DTO 변환 후 반환
        return ProductDTO.fromEntity(saved);
    }
}