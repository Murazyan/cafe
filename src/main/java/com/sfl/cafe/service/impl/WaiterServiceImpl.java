package com.sfl.cafe.service.impl;

import com.sfl.cafe.model.User;
import com.sfl.cafe.model.enums.UserType;
import com.sfl.cafe.repository.UserRepository;
import com.sfl.cafe.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class WaiterServiceImpl implements WaiterService {

    private UserRepository userRepository;

    @Autowired
    public WaiterServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllWaiters() {
        return userRepository.findAllByType(UserType.WAITER);
    }
}
