package com.peters.Epay.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductRequestDto {
    @Schema(example = "Iphone13Pro Max", format = "name")
    private String name;
    @Schema(example = "256G Iphone13Pro Max", format = "description")
    private String description;
    @Schema(example = "N480,000.00", format = "double")
    private Double price;
    @Schema(example = "2 pieces", format = "integer")
    private int unit;
}
