package com.example.springmappingobjectsexercise.entities.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class GameAddDto {

    private String tittle;
    private BigDecimal price;
    private Double size;
    private String trailer;
    private String thumbnailUrl;
    private String description;
    private LocalDate releaseDate;

    public GameAddDto() {
    }

    public GameAddDto(String tittle, BigDecimal price, Double size,
                      String trailer, String thumbnailUrl,
                      String description, LocalDate releaseDate) {
        this.tittle = tittle;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    @Pattern(regexp = "[A-Z][a-z].{3,100}", message = "Invalid tittle.")
    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    @DecimalMin(value = "0", message = "Enter valid price.")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Positive
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Pattern(regexp = "\\w{11}",
            message = "Invalid YouTube URL")
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Pattern(regexp = "(https?).+", message = "Invalid URL.")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Size(min = 20, message = "Description is minimum 20 characters.")
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
