package com.example.product_api.controller;

import com.example.product_api.dto.ProductDTO;
import com.example.product_api.dto.ProductResponse;
import com.example.product_api.model.Product;
import com.example.product_api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "Obtener a lista de productos",
            description = "Retorna la lista de productos"
    )
    @ApiResponse(responseCode = "200", description = "lista de productos")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> createProduct(@Valid @PathVariable Long id,
                                                 @RequestBody ProductDTO dto){
        return ResponseEntity.ok(productService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.ok("Producto eliminado");
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ProductResponse> updateProductAvailableById(@PathVariable Long id,
                                                 @RequestBody Boolean status){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.updateStatus(id, status));
    }

    @GetMapping("/available")
    public ResponseEntity<List<ProductResponse>> getAvailableProducts(){
        return ResponseEntity.ok(productService.findByAvailableTrue());
    }
}
