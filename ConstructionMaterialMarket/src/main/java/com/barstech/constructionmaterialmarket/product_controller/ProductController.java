package com.barstech.constructionmaterialmarket.product_controller;

import com.barstech.constructionmaterialmarket.product_model.Product;
import com.barstech.constructionmaterialmarket.product_service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController("/api")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> listOfProduct() {
       List<Product> list = productService.listOfProduct();
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        if (product != null) {
            productService.saveProduct(product);
            return new ResponseEntity<>("Product Crated...", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Bad Request.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/products/{id}/update")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updated) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>("Product not Found", HttpStatus.NOT_FOUND);
        } else {
            product.setName(updated.getName());
            // Diğer alanları güncelleyin
            productService.saveProduct(product);
            return new ResponseEntity<>("Product is Updated", HttpStatus.OK);
        }
    }

    @GetMapping("/products/{id}/found")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    @GetMapping("/products/{id}/delete")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>("Product Not Found",HttpStatus.NOT_FOUND);
        } else {
            productService.deleteProductById(product);
            return new ResponseEntity<>("Product Deleted...", HttpStatus.OK);
        }
    }
}
