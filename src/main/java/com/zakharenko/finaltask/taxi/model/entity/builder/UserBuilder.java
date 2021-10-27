package com.zakharenko.finaltask.taxi.model.entity.builder;

import com.zakharenko.finaltask.taxi.model.entity.User;

public interface UserBuilder {
    UserBuilder setId(int id);

    UserBuilder setLogin(String login);

    UserBuilder setPassword(String password);

    UserBuilder setEmail (String email);

    UserBuilder setPhone(String phone_number);

    UserBuilder setStatus(String status);

    UserBuilder setRole(String role);

    User build();
}
