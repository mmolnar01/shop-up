package com.mmolnar.ShopUp2.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @RequestMapping("/")
    fun greeting(): String {
        return "Welcome to ShopUp!"
    }

    @RequestMapping("/about")
    fun about(): String {
        return "About Page"
    }

    @GetMapping("/csrf-token")
    fun getCsrfToken(request: HttpServletRequest): CsrfToken {
        return request.getAttribute("_csrf") as CsrfToken
    }
}