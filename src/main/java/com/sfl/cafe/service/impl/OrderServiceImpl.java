package com.sfl.cafe.service.impl;

import com.sfl.cafe.model.Order;
import com.sfl.cafe.model.Product;
import com.sfl.cafe.model.ProductInOrder;
import com.sfl.cafe.model.Table;
import com.sfl.cafe.model.enums.OrderStatus;
import com.sfl.cafe.model.enums.ProductInOrderStatus;
import com.sfl.cafe.repository.OrderRepository;
import com.sfl.cafe.repository.ProductInOrderRepository;
import com.sfl.cafe.repository.ProductRepository;
import com.sfl.cafe.repository.TableRepository;
import com.sfl.cafe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private TableRepository tableRepository;
    private ProductInOrderRepository productInOrderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,TableRepository tableRepository,
                            ProductInOrderRepository productInOrderRepository, ProductRepository productRepository){
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
        this.productInOrderRepository = productInOrderRepository;
        this.productRepository = productRepository;
    }


    /**
     * Add new empty order for specified table
    * */
    @Override
    public void addNewOrder(Table table) {
        orderRepository.save(Order.builder()
                .status(OrderStatus.OPEN)
                .table(table)
                .build());
        tableRepository.updateStatus(false,table.getId() );

    }

    /**
     *  Get orders list by tables
    * */
    @Override
    public List<Order> getOrdersForTables(List<Table> tablesForWaiter) {
        return orderRepository.findAllByTableIn(tablesForWaiter);
    }

    @Override
    public boolean addNewProductToOrder(int orderId, int productId, int amount) {
        boolean isSaved = false;
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order!=null && order.getStatus()==OrderStatus.OPEN && productRepository.existsById(productId)){
            productInOrderRepository.save(ProductInOrder.builder()
                    .amount(amount)
                    .product(Product.builder().id(productId).build())
                    .order(order)
                    .status(ProductInOrderStatus.ACTIVE)
                    .build());
        }
        return isSaved;
    }
}
