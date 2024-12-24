package com.byschoo.apirest_service_postgres.config;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductEntity> getProductsController() {
        return this.productService.getProductsService();
    }

    @PostMapping
    public ResponseEntity<Object> postProductController(@RequestBody ProductEntity productEntity){
        return this.productService.postProductService(productEntity);
    }

    //Para actualizar se usa el mismo método en el Service, diferenciando si tiene id o no, entonces actualizará
    @PutMapping
    public ResponseEntity<Object> putProductController(@RequestBody ProductEntity productEntity){
        return this.productService.postProductService(productEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductController(@PathVariable Long id) {
        return this.productService.deleteProductService(id);
    }
}
