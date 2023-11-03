package com.barstech.constructionmaterialmarket.product_service.implementation;

import com.barstech.constructionmaterialmarket.product_model.Product;
import com.barstech.constructionmaterialmarket.product_repository.ProductRepository;
import com.barstech.constructionmaterialmarket.product_service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;


    @Override
    public List<Product> listOfProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(Product product) {
        productRepository.delete(product);
    }
}
