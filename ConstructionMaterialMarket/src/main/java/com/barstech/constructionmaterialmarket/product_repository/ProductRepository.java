package com.barstech.constructionmaterialmarket.product_repository;

import com.barstech.constructionmaterialmarket.product_model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
