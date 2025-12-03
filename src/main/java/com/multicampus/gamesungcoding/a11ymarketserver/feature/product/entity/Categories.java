package com.multicampus.gamesungcoding.a11ymarketserver.feature.product.entity;

import com.multicampus.gamesungcoding.a11ymarketserver.common.id.UuidV7;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Categories {
    @Id
    @UuidV7
    @Column(length = 16, nullable = false, updatable = false)
    private UUID categoryId;

    @Column(length = 16)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UUID parentCatId;

    @Column(length = 200, nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @Builder
    private Categories(
            UUID parentCatId,
            String categoryName) {
        this.parentCatId = parentCatId;
        this.categoryName = categoryName;
    }
}
