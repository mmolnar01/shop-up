package com.mmolnar.ShopUp2.controller

import com.mmolnar.ShopUp2.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/api/users")
class UserController {
    @Autowired
    lateinit var userService: UserService
}