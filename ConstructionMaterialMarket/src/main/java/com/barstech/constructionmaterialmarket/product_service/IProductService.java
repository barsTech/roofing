package com.barstech.constructionmaterialmarket.product_service;

import com.barstech.constructionmaterialmarket.product_model.Product;

import java.util.List;

public interface IProductService {
    List<Product> listOfProduct();

    Product getProductById(Long id);

    void saveProduct(Product product);

    void deleteProductById(Product product);
}
