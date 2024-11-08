package com.example.cathayunited.param;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class CreatePriceByDateParam {

    @Schema(description = "id", example = "10480016", required = true)
    private String id;

    @Schema(description = "From date", example = "2023/03/13 00:00:00")
    private String Date;

    @Schema(description = "price", example = "1.23")
    private BigDecimal price;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
