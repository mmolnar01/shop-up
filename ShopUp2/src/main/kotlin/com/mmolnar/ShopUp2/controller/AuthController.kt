package com.mmolnar.ShopUp2.controller

import com.mmolnar.ShopUp2.config.SecurityConfig
import com.mmolnar.ShopUp2.dto.AuthResponse
import com.mmolnar.ShopUp2.dto.LoginRequest
import com.mmolnar.ShopUp2.dto.SignUpRequest
import com.mmolnar.ShopUp2.model.User
import com.mmolnar.ShopUp2.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus

@RestController
@RequestMapping("/auth")
class AuthController {
    @Autowired
    private final lateinit var userService: UserService
    @Autowired
    private final lateinit var passwordEncoder : PasswordEncoder


    @PostMapping("/authenticate")
    fun login(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<AuthResponse> {
        val userOptional = userService.validUsernameAndPassword(loginRequest.username, loginRequest.password)
        if (userOptional.isPresent) {
            val user = userOptional.get()
            return ResponseEntity.ok(AuthResponse(user.id, user.username, user.role))
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun signUp(@Valid @RequestBody signUpRequest: SignUpRequest): AuthResponse {
        if (userService.hasUserWithUsername(signUpRequest.username)) {
            throw Exception(String.format("Username %s is already been used", signUpRequest.username))
        }
        if (userService.hasUserWithEmail(signUpRequest.email)) {
            throw Exception(String.format("Email %s is already been used", signUpRequest.email));
        }

        val user = userService.saveUser(this.mapSignUpRequestToUSer(signUpRequest))
        return AuthResponse(user.id, user.username, user.role)
    }

    private fun mapSignUpRequestToUSer(signUpRequest: SignUpRequest): User {
        val user = User(
            username = signUpRequest.username,
            password = passwordEncoder.encode(signUpRequest.password),
            name = signUpRequest.name,
            email = signUpRequest.email,
            role = SecurityConfig.USER
        )
        return user
    }
}