CREATE TABLE users
(
    `id`       int(11) NOT NULL auto_increment,
    `name`     VARCHAR(255) NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `type`     INT(11) NOT NULL,
    PRIMARY KEY (id)
)ENGINE=InnoDB;

CREATE TABLE `products`
(
    `id`    int(11) NOT NULL auto_increment,
    `name`  VARCHAR(255) NOT NULL,
    `price` FLOAT        NOT NULL,
    PRIMARY KEY (id)
)ENGINE=InnoDB;

CREATE TABLE `tables`
(
    `id`        int(11) NOT NULL auto_increment,
    `open`      tinyint(1) NOT NULL default '1',
    `waiter_id` int(11) NOT NULL,
    PRIMARY KEY (`id`),
    KEY         `waiter_id` (`waiter_id`),
    CONSTRAINT `tables_user_fk_1` FOREIGN KEY (`waiter_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB;


CREATE TABLE `orders`
(
    `id`       INT(11) NOT NULL AUTO_INCREMENT,
    `status`   INT(11) NOT NULL DEFAULT '1',
    `table_id` INT(11) NOT NULL,
    PRIMARY KEY (`id`),
    KEY        `table_id` (`table_id`),
    CONSTRAINT `orders_tables_fk_1` FOREIGN KEY (`table_id`) REFERENCES `tables` (`id`)
) ENGINE=INNODB;

CREATE TABLE `product_in_order` (
     `id` INT(11) NOT NULL AUTO_INCREMENT,
     `amount` INT(11) NOT NULL DEFAULT '1',
     `status` INT(11) NOT NULL DEFAULT '1',
     `order_id` INT(11) NOT NULL,
     `product_id` INT(11) NOT NULL,
     PRIMARY KEY  (`id`),
     KEY `order_id` (`order_id`),
     KEY `product_id` (`product_id`),
     CONSTRAINT `product_in_order_products_fk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
     CONSTRAINT `product_in_order_orders_fk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=INNODB;
