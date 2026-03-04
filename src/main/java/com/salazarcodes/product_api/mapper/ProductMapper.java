package com.salazarcodes.product_api.mapper;

import com.salazarcodes.product_api.dto.ProductDTO;
import com.salazarcodes.product_api.dto.ProductResponse;
import com.salazarcodes.product_api.model.Product;

public class ProductMapper {

    public static Product toProduct(ProductDTO dto){
        return new Product(
            dto.name(),
            dto.price(),
            dto.available()
        );
    }

    public static ProductResponse toResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getAvailable()
        );
    }
}
