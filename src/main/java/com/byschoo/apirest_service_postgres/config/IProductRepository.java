package com.byschoo.apirest_service_postgres.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long>{
    
    //Preguntar por el producto antes de guardarlo, buscando a través del nombre 
    Optional<ProductEntity> findProductByName(String name);
}
