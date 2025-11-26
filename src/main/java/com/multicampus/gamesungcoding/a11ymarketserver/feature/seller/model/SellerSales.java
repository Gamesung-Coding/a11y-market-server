package com.multicampus.gamesungcoding.a11ymarketserver.feature.seller.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "SELLER_SALES_VIEW")
public class SellerSales {

    @Id
    @Column(name = "SELLER_ID", nullable = false, length = 16)
    private UUID sellerId;

    @Column(name = "TOTAL_SALES")
    private Integer totalSales;

    @Column(name = "TOTAL_ORDERS")
    private Integer totalOrders;

    @Column(name = "TOTAL_PRODUCTS_SOLD")
    private Integer totalProductsSold;

    @Column(name = "TOTAL_CANCELLED")
    private Integer totalCancelled;

    protected SellerSales() {
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public Integer getTotalProductsSold() {
        return totalProductsSold;
    }

    public Integer getTotalCancelled() {
        return totalCancelled;
    }
}
