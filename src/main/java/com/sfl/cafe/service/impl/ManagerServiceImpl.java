package com.sfl.cafe.service.impl;

import com.sfl.cafe.model.Product;
import com.sfl.cafe.model.Table;
import com.sfl.cafe.model.User;
import com.sfl.cafe.repository.ProductRepository;
import com.sfl.cafe.repository.TableRepository;
import com.sfl.cafe.repository.UserRepository;
import com.sfl.cafe.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ManagerServiceImpl implements ManagerService {

    private UserRepository userRepository;
    private TableRepository tableRepository;
    private ProductRepository productRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ManagerServiceImpl(UserRepository userRepository, TableRepository tableRepository,
                              ProductRepository productRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.tableRepository = tableRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public Table saveTable(Table table) {
        return tableRepository.save(table);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Table assignTableToWaiter(Table table, User user) {
        tableRepository.setWaiterToTale(table.getId(), user);
        table.setWaiter(user);
        return table;
    }

    @Override
    public boolean addNewUser(User user) {
        boolean isSaved = false;
        if(userRepository.findUserByUsername(user.getUsername())==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            isSaved =true;
        }
        return isSaved;
    }


}
