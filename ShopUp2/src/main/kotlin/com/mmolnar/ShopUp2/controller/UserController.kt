package com.mmolnar.ShopUp2.controller

import com.mmolnar.ShopUp2.model.User
import com.mmolnar.ShopUp2.security.CustomUserDetails
import com.mmolnar.ShopUp2.service.ProductService
import com.mmolnar.ShopUp2.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/api/users")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/me")
    fun getCurrentUser(@AuthenticationPrincipal currentUser: CustomUserDetails): User {
        return userService.validateAndGetUserByUsername(currentUser.username)
    }

    @GetMapping
    fun getUsers() : List<User> {
        return userService.getUsers()
    }

    @GetMapping("/{username}")
    fun getUser(@PathVariable username: String): User {
        return userService.validateAndGetUserByUsername(username)
    }

    @DeleteMapping("/{username}")
    fun deleteUser(@PathVariable username: String): User {
        val user = userService.validateAndGetUserByUsername(username)
        userService.deleteUser(user)
        return user
    }
}