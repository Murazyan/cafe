package com.sfl.cafe.service;

import com.sfl.cafe.model.Product;
import com.sfl.cafe.model.ProductInOrder;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);

    List<Product> getAllProducts();

    boolean updateProductInOrder(ProductInOrder productInOrder);
}
