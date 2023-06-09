package com.peters.Epay.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.peters.Epay.product.entity.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int unit;
    private List<ProductCategory> categories;
    private List<String> productImagePath;
}
