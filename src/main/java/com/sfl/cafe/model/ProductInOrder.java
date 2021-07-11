package com.sfl.cafe.model;

import com.sfl.cafe.model.enums.ProductInOrderStatus;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "product_in_order")
@ToString
public class ProductInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    @Column
    private int amount;

    @Enumerated(EnumType.ORDINAL)
    private ProductInOrderStatus status;
}
