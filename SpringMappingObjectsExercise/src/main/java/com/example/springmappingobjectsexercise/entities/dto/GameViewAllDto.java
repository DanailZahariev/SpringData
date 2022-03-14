package com.example.springmappingobjectsexercise.entities.dto;

import java.math.BigDecimal;

public class GameViewAllDto {

    private String tittle;
    private BigDecimal price;

    public GameViewAllDto(String tittle, BigDecimal price) {
        this.tittle = tittle;
        this.price = price;
    }

    public GameViewAllDto() {
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
