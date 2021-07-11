package com.sfl.cafe.service;

import com.sfl.cafe.model.Order;
import com.sfl.cafe.model.Table;

import java.util.List;

public interface OrderService {
    void addNewOrder(Table table);

    List<Order> getOrdersForTables(List<Table> tablesForWaiter);

    boolean addNewProductToOrder(int orderId, int productId, int amount);
}
