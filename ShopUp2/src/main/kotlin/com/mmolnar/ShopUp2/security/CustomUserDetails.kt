package com.mmolnar.ShopUp2.security

import lombok.Data
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.GrantedAuthority

@Data
class CustomUserDetails : UserDetails {
    private var id: Int = 0
    private lateinit var username: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var name: String
    private lateinit var authorities: Collection<out GrantedAuthority>

    override fun getAuthorities(): Collection<out GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }
}