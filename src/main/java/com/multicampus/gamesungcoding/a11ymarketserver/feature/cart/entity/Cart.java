package com.multicampus.gamesungcoding.a11ymarketserver.feature.cart.entity;

import com.multicampus.gamesungcoding.a11ymarketserver.common.id.UuidV7;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.user.entity.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Table(name = "carts")
public class Cart {
    @Id
    @UuidV7
    @Column(length = 16, updatable = false, nullable = false)
    private UUID cartId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false, nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users user;

    @Builder
    private Cart(Users user) {
        this.user = user;
    }
}
