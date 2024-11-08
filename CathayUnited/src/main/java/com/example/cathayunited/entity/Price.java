package com.example.cathayunited.entity;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer priceId;

    private String id;
    private Long date;

    private BigDecimal price;
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    public Price() {}

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = new Timestamp(System.currentTimeMillis());
        }
    }

    public Price(String id, Long date, BigDecimal price) {
        this.id = id;
        this.date = date;
        this.price = price;
    }

    public Integer getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
