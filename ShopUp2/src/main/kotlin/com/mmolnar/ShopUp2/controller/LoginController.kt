package com.mmolnar.ShopUp2.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {
    @RequestMapping("/login")
    fun login(): String {
        return "Login Page"
    }
}