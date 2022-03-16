package com.jsonprocessing.service;

import com.jsonprocessing.model.dto.CategoryProductStatsDto;
import com.jsonprocessing.model.dto.ProductInRangeDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void seedProducts() throws IOException;

    List<ProductInRangeDto> findAllProductsInRangeOrderByPrice(BigDecimal lower, BigDecimal upper);

    List<CategoryProductStatsDto> getCategoryStats();
}
