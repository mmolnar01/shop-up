package com.mmolnar.ShopUp2.controller

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
}