package com.example.springmappingobjectsexercise.entities.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameDetailDto {

    private String tittle;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;

    public GameDetailDto(String tittle, BigDecimal price, String description, LocalDate releaseDate) {
        this.tittle = tittle;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public GameDetailDto() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
