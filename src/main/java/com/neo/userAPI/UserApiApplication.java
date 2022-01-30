package com.neo.userAPI;

import com.neo.userAPI.entity.UserEntity;
import com.neo.userAPI.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserApiApplication implements CommandLineRunner {


    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(UserApiApplication.class, args);
    }

    @Override
    public void run(String... args) {
        userRepository.deleteAll();
        userRepository.save(new UserEntity(1122L, "Neo", passwordEncoder.encode("Meow"), "READ_ACCESS,WRITE_ACCESS,ADMIN_ACCESS"));
        userRepository.save(new UserEntity(1123L, "Leo", passwordEncoder.encode("Woof"), "READ_ACCESS"));

    }


}
