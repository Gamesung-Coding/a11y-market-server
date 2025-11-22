package com.multicampus.gamesungcoding.a11ymarketserver.admin.seller.service;

import com.multicampus.gamesungcoding.a11ymarketserver.feature.seller.model.Seller;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.seller.model.SellerApplyResponse;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.seller.model.SellerSubmitStatus;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminSellerService {

    private final SellerRepository sellerRepository;

    public List<SellerApplyResponse> inquirePendingSellers() {

        List<Seller> pendingList = sellerRepository.findBySellerSubmitStatus(SellerSubmitStatus.PENDING.getStatus());

        return pendingList.stream()
                .map(SellerApplyResponse::fromEntity)
                .toList();
    }

}
