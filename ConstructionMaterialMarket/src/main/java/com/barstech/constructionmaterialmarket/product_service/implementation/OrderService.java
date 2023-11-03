package com.barstech.constructionmaterialmarket.product_service.implementation;

import com.barstech.constructionmaterialmarket.product_model.Category;
import com.barstech.constructionmaterialmarket.product_model.Order;
import com.barstech.constructionmaterialmarket.product_model.Product;
import com.barstech.constructionmaterialmarket.product_repository.CategoryRepository;
import com.barstech.constructionmaterialmarket.product_repository.OrderRepository;
import com.barstech.constructionmaterialmarket.product_repository.ProductRepository;
import com.barstech.constructionmaterialmarket.product_service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Order> listOfOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public void cancelOrder(Order order) {
        List<Product> cancelledProducts = order.getProducts();

        for (Product product : cancelledProducts) {
            productRepository.save(product);

            Category category = product.getCategory();
            categoryRepository.save(category);
        }
        orderRepository.delete(order);
    }
}
