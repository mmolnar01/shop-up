package com.mmolnar.ShopUp2.controller

import com.mmolnar.ShopUp2.config.SwaggerConfig.Companion.BASIC_AUTH_SECURITY_SCHEME
import com.mmolnar.ShopUp2.dto.UserDto
import com.mmolnar.ShopUp2.model.User
import com.mmolnar.ShopUp2.security.CustomUserDetails
import com.mmolnar.ShopUp2.service.ProductService
import com.mmolnar.ShopUp2.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
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
@RequestMapping("/api/users")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @Operation(security = [SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)])
    @GetMapping("/me")
    fun getCurrentUser(@AuthenticationPrincipal currentUser: CustomUserDetails): UserDto {
        return UserDto.from(userService.validateAndGetUserByUsername(currentUser.username))
        //return userService.validateAndGetUserByUsername(currentUser.username)
    }

    @Operation(security = [SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)])
    @GetMapping
    fun getUsers() : List<User> {
        return userService.getUsers()
    }

    @Operation(security = [SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)])
    @GetMapping("/{username}")
    fun getUser(@PathVariable username: String): User {
        return userService.validateAndGetUserByUsername(username)
    }

    @Operation(security = [SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)])
    @DeleteMapping("/{username}")
    fun deleteUser(@PathVariable username: String): User {
        val user = userService.validateAndGetUserByUsername(username)
        userService.deleteUser(user)
        return user
    }
}