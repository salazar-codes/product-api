package com.salazarcodes.product_api.service;

import com.salazarcodes.product_api.dto.ProductDTO;
import com.salazarcodes.product_api.dto.ProductResponse;
import com.salazarcodes.product_api.mapper.ProductMapper;
import com.salazarcodes.product_api.model.Product;
import com.salazarcodes.product_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> findAll(){
        //return productRepository.findAll();
        return productRepository.findAll().stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    public ProductResponse findById(Long id){
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Product not found")
        );
        return ProductMapper.toResponse(product);
    }

    public ProductResponse save (ProductDTO dto){
        Product product = ProductMapper.toProduct(dto);
        return ProductMapper.toResponse(productRepository.save(product));
    }

    public ProductResponse update (Long id, ProductDTO updated){
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Product not found")
        );
        //product.setName(updated.getName());
        product.setName(updated.name());
        product.setAvailable(updated.available());
        product.setPrice(updated.price());
        return ProductMapper.toResponse(productRepository.save(product));
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

    public ProductResponse updateStatus(Long id, Boolean status){
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Product not found")
        );
        product.setAvailable(status);
        return ProductMapper.toResponse(productRepository.save(product));
    }

    public List<ProductResponse> findByAvailableTrue(){
        return productRepository.findByAvailableTrue().stream()
                .map(ProductMapper::toResponse)
                .toList();
    }
}
