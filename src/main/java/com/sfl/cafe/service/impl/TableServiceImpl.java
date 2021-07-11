package com.sfl.cafe.service.impl;

import com.sfl.cafe.model.Order;
import com.sfl.cafe.model.Table;
import com.sfl.cafe.model.User;
import com.sfl.cafe.repository.OrderRepository;
import com.sfl.cafe.repository.TableRepository;
import com.sfl.cafe.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TableServiceImpl implements TableService {

    private TableRepository tableRepository;
    private OrderRepository orderRepository;

    @Autowired
    public TableServiceImpl(TableRepository tableRepository, OrderRepository orderRepository) {
        this.tableRepository = tableRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Table> getTablesForWaiter(User user) {
        return tableRepository.findAllByWaiter(user);
    }

    @Override
    public boolean addOrderToTable(Order order, Table table) {
        boolean result = true;
        if (table.isOpen()) {
            orderRepository.setOrderToTable(order.getId(), table);
            table.setOpen(false); // now this table is not open, (change table status)
            tableRepository.save(table);
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public List<Table> getFreeTables() {
        return tableRepository.findAllByOpen(true);
    }

    @Override
    public Table addTable(Table table) {
        table.setOpen(true);
        return tableRepository.save(table);
    }

    @Override
    public void assignTableToWaiter(int tableId, int waiterId) {
        tableRepository.setWaiterToTale(tableId, User.builder().id(waiterId).build());
//        tableRepository.updateStatus(false,tableId); // now this table is not open, (change table status)
    }
}
