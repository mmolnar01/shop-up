package com.mmolnar.ShopUp2.service

import com.mmolnar.ShopUp2.model.User
import com.mmolnar.ShopUp2.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository
    private lateinit var passwordEncoder: PasswordEncoder

    fun getUsers(): List<User> {
        return userRepository.findAll()
    }

    fun getUserByUsername(username: String): Optional<User> {
        return userRepository.findByUsername(username)
    }

    fun hasUserWithUsername(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }

    fun hasUserWithEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    fun validateAndGetUserByUsername(username: String): User {
        return getUserByUsername(username).orElseThrow { UsernameNotFoundException("Username $username not found") }
    }

    fun saveUser(user: User): User {
        return userRepository.save(user)
    }

    fun deleteUser(user: User) {
        userRepository.delete(user)
    }

    fun validUsernameAndPassword(username: String, password: String): Optional<User> {
        return getUserByUsername(username).filter { passwordEncoder.matches(password, it.password) }
    }
}