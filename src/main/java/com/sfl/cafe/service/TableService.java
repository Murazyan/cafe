package com.sfl.cafe.service;

import com.sfl.cafe.model.Order;
import com.sfl.cafe.model.Table;
import com.sfl.cafe.model.User;

import java.util.List;

public interface TableService {


    List<Table> getTablesForWaiter(User user);

    boolean addOrderToTable(Order order, Table table);

    List<Table> getFreeTables();

    Table addTable(Table table);

    void assignTableToWaiter(int tableId, int waiterId);

}
