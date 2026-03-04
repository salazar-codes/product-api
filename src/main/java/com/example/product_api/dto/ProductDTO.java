package com.example.product_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductDTO (

        @Schema(
                description = "Nombre del producto",
                example = "chicles",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank
        String name,

        @Schema(
                description = "Precio del producto",
                example = "2.5",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @Positive Double price,

        @NotNull
        Boolean available
) {}

// Traditional mode
/*
@Getter
@Setter
public class ProductDTO {

    @NotBlank
    private String name;

    @Positive
    private Double price;

    @NotNull
    private Boolean available;
}
*/