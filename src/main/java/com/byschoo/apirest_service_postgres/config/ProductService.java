package com.byschoo.apirest_service_postgres.config;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository){
        this.productRepository=productRepository;
    }

    //GET SERVICE
    public List<ProductEntity> getProductsService() {
        return productRepository.findAll();
    }

    //POST SERVICE
    public ResponseEntity<Object> postProductService(ProductEntity productEntity) {
        Optional<ProductEntity> response = this.productRepository.findProductByName(productEntity.getName());        
        
        //El HashMap con valores String y Object va permitir mensages de cualquier tipo: "error", true or "mess", "text"
        HashMap<String, Object> mess = new HashMap<>();
        
        if (response.isPresent() && productEntity.getId()==null) {
            
            mess.put("error", true);
            mess.put("message", "The product cannot be saved. It already exists.");
            
            return new ResponseEntity<>(mess, HttpStatus.CONFLICT);
        }
        //UPDATE SERVICE .-Only this else if because the save method can also update data
        else if (productEntity.getId()!=null) {
            mess.put("execution", "Successful");
            mess.put("data updated", productEntity);            
            
            productRepository.save(productEntity);
            return new ResponseEntity<>(mess, HttpStatus.ACCEPTED);
        }
        else{
            mess.put("execution", "Successful");
            mess.put("data saved", productEntity);
            
            productRepository.save(productEntity);
            return new ResponseEntity<>(mess, HttpStatus.CREATED);
        }
        
        
    }

    public ResponseEntity<Object> deleteProductService(Long id) {

        //El HashMap con valores String y Object va permitir mensages de cualquier tipo: "error", true or "mess", "text"
        HashMap<String, Object> mess = new HashMap<>();

        boolean exist = this.productRepository.existsById(id);
        
        if (!exist) {
            mess.put("error", true);
            mess.put("message", "The product cannot be deleted. This ID does not exist.");
            
            return new ResponseEntity<>(mess, HttpStatus.CONFLICT);
        } else {
            mess.put("execution", "Successful");
            mess.put("message", "product deleted");            
            
            productRepository.deleteById(id);
            return new ResponseEntity<>(mess, HttpStatus.ACCEPTED);            
        }

    }

}
