package com.sfl.cafe.repository;


import com.sfl.cafe.model.Order;
import com.sfl.cafe.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update Order o set o.table=:table where o.id=:orderId")
    void setOrderToTable(@Param("orderId") int orderId,
                         @Param("table") Table table);


    List<Order> findAllByTableIn(List<Table> tables);
}
