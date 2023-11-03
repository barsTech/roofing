package com.barstech.constructionmaterialmarket.product_service;

import com.barstech.constructionmaterialmarket.product_model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> listOfOrder();

    Order getOrderById(Long id);

    void saveOrder(Order order);

    void cancelOrder(Order order);
}
