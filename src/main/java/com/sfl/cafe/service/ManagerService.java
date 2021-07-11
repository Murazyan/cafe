package com.sfl.cafe.service;

import com.sfl.cafe.model.Product;
import com.sfl.cafe.model.Table;
import com.sfl.cafe.model.User;

public interface ManagerService {

    User saveUser(User user);

    Table saveTable(Table table);

    Product saveProduct(Product product);

    Table assignTableToWaiter(Table table, User user);

    boolean addNewUser(User user);
}
