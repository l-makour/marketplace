package com.checkconsulting.marketplace.controller;
import com.checkconsulting.marketplace.modelDto.ProductDto;
import com.checkconsulting.marketplace.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Integer id){
        ProductDto productDto=productService.getById(id);
        if (productDto==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        }
    }
}