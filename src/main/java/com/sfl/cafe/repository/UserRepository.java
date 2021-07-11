package com.sfl.cafe.repository;

import com.sfl.cafe.model.User;
import com.sfl.cafe.model.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    List<User> findAllByType(UserType type);

    User findUserByUsername(String username);
}
