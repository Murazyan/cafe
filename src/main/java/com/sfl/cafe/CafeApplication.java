package com.sfl.cafe;

import com.sfl.cafe.model.User;
import com.sfl.cafe.model.enums.UserType;
import com.sfl.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CafeApplication implements CommandLineRunner {

    @Autowired
    @Lazy
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(CafeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findUserByUsername("manager1")==null){
            userRepository.save(User.builder()
                    .name("Manager 1")
                    .username("manager1")
                    .password(passwordEncoder.encode("147852"))
                    .type(UserType.MANAGER)
                    .build());
        }
        if(userRepository.findUserByUsername("waiter1")==null){
            userRepository.save(User.builder()
                    .name("Waiter 1")
                    .username("waiter1")
                    .password(passwordEncoder.encode("147852"))
                    .type(UserType.MANAGER)
                    .build());
        }
    }
}
