package com.kodilla.ecommercee.security.config;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.enums.AppRoles;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CmdLineRunnerAdmin implements CommandLineRunner {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public CmdLineRunnerAdmin(UserRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<User> user = repository.findByUsername("admin");
        if(!user.isPresent()){
            User admin = new User("admin", encoder.encode("admin"), AppRoles.ROOT);
            repository.save(admin);
        }
    }
}

