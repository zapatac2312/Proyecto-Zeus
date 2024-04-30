package com.demo.Util;

import com.demo.Modelo.Authority;
import com.demo.Modelo.User;
import com.demo.Repository.AuthorityRepository;
import com.demo.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Runner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final AuthorityRepository rolesRepository;

    public Runner(UserRepository userRepository, AuthorityRepository rolesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.rolesRepository.count() == 0) {
            this.rolesRepository.saveAll(List.of(
                    new Authority(AuthorityName.ADMIN),
                    new Authority(AuthorityName.READ),
                    new Authority(AuthorityName.WRITE)
            ));
        }

        if (this.userRepository.count() == 0){
            var encoders = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            this.userRepository.saveAll(List.of(
                            new User("admin",encoders.encode("password123"),List.of(this.rolesRepository.findByName(AuthorityName.ADMIN).get())),
                            new User("user1",encoders.encode("password123"),List.of(this.rolesRepository.findByName(AuthorityName.READ).get())),
                            new User("user2",encoders.encode("password456"),List.of(this.rolesRepository.findByName(AuthorityName.WRITE).get()))
                    )
            );

        }
    }
}
