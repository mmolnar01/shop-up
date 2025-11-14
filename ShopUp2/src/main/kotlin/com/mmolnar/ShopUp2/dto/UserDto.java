package com.mmolnar.ShopUp2.dto;

import com.mmolnar.ShopUp2.model.User;

public record UserDto(int id, String username, String name, String email, String role) {

    public static UserDto from(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
