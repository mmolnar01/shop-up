package com.mmolnar.ShopUp2.service

import com.mmolnar.ShopUp2.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository
    private lateinit var passwordEncoder: PasswordEncoder
}