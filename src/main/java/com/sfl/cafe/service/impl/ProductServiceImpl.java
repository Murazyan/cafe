package com.sfl.cafe.service.impl;

import com.sfl.cafe.model.Product;
import com.sfl.cafe.model.ProductInOrder;
import com.sfl.cafe.repository.ProductInOrderRepository;
import com.sfl.cafe.repository.ProductRepository;
import com.sfl.cafe.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductInOrderRepository productInOrderRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductInOrderRepository productInOrderRepository){
        this.productRepository = productRepository;
        this.productInOrderRepository = productInOrderRepository;
    }


    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public boolean updateProductInOrder(ProductInOrder productInOrder) {
         productInOrderRepository.save(productInOrder);
        return true;
    }
}
