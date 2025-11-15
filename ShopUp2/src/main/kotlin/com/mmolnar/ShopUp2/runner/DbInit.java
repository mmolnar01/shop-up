package com.mmolnar.ShopUp2.runner;

import com.mmolnar.ShopUp2.config.SecurityConfig;
import com.mmolnar.ShopUp2.model.User;
import com.mmolnar.ShopUp2.service.ProductService;
import com.mmolnar.ShopUp2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class DbInit implements CommandLineRunner {

    private final UserService userService;
    private final ProductService bookService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userService.getUsers().isEmpty()) {
            return;
        }

        USERS.forEach(user -> {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
        });

        log.info("Database initialized");
    }

    private static final List<User> USERS = Arrays.asList(
            new User(null, "admin", "admin@shopup.com", "admin" , "Admin", SecurityConfig.ADMIN),
            new User(null, "user", "user@shopup.com", "user", "User", SecurityConfig.USER)
    );
}
