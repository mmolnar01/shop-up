package com.mmolnar.ShopUp2.controller

import com.mmolnar.ShopUp2.service.UserService
import lombok.RequiredArgsConstructor
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import jakarta.validation.Valid

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
class AuthController {
    private final lateinit var userService: UserService
    private final lateinit var passwordEncoder : PasswordEncoder


}