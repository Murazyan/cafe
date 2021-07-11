package com.sfl.cafe.repository;

import com.sfl.cafe.model.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Integer> {
}
