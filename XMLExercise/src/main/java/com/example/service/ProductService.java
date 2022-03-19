package com.example.service;

import com.example.model.dtos.ProductSeedDto;
import com.example.model.dtos.ProductViewRootDto;

import java.util.List;

public interface ProductService {

    long getCount();

    void seedProducts(List<ProductSeedDto> products);

    ProductViewRootDto findProductInRange();
}
