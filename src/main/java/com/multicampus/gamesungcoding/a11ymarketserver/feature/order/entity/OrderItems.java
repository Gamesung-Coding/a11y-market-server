package com.multicampus.gamesungcoding.a11ymarketserver.feature.order.entity;

import com.multicampus.gamesungcoding.a11ymarketserver.common.exception.InvalidRequestException;
import com.multicampus.gamesungcoding.a11ymarketserver.common.id.UuidV7;
import com.multicampus.gamesungcoding.a11ymarketserver.feature.product.entity.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ORDER_ITEMS")
public class OrderItems {

    @Id
    @UuidV7
    @Column(length = 16, nullable = false, updatable = false)
    private UUID orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", updatable = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Product product;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer productPrice;

    @Column(nullable = false)
    private Integer productQuantity;

    @Column
    private String productImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private OrderItemStatus orderItemStatus;

    @Lob
    @Column
    private String cancelReason;

    @Builder
    private OrderItems(Orders order,
                       Product product,
                       String productName,
                       Integer productPrice,
                       Integer productQuantity,
                       String productImageUrl) {

        this.order = order;
        this.product = product;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productImageUrl = productImageUrl;
        this.orderItemStatus = OrderItemStatus.ORDERED;
        this.cancelReason = null;
    }

    public void cancelOrderItem(String reason) {
        if (this.orderItemStatus == OrderItemStatus.SHIPPED ||
                this.orderItemStatus == OrderItemStatus.CONFIRMED ||
                this.orderItemStatus == OrderItemStatus.RETURNED) {
            throw new InvalidRequestException("이미 배송된 상품은 취소할 수 없습니다.");
        }

        if (this.orderItemStatus == OrderItemStatus.CANCEL_PENDING ||
                this.orderItemStatus == OrderItemStatus.CANCELED ||
                this.orderItemStatus == OrderItemStatus.RETURN_PENDING) {
            throw new InvalidRequestException("이미 취소 요청이 진행 중이거나 완료된 상품입니다.");
        }
        this.orderItemStatus = OrderItemStatus.CANCELED;
        this.cancelReason = reason;
    }

    public void updateOrderItemStatus(OrderItemStatus status) {
        this.orderItemStatus = status;
    }
}
