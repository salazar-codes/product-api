package com.salazarcodes.product_api.dto;

public record ProductResponse(
        Long id,
        String name,
        Double price,
        Boolean available
) {}
