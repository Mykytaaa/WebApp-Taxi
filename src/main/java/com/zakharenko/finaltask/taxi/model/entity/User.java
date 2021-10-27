package com.zakharenko.finaltask.taxi.model.entity;

import com.zakharenko.finaltask.taxi.model.entity.builder.UserBuilder;

public class User extends Entity {
    private String login;
    private String password;
    private String email;
    private String phone_number;
    private String role;
    private String status;

    public User(){}

    private User(User.UserBuilderImpl builder){
        super(builder.id);
        this.login = builder.login;
        this.password = builder.password;
        this.email = builder.email;
        this.phone_number = builder.phone_number;
        this.role = builder.role;
        this.status = builder.status;
    }

    public static class UserBuilderImpl implements UserBuilder {
        private int id;
        private String login;
        private String password;
        private String email;
        private String phone_number;
        private String role;
        private String status;

        @Override
        public UserBuilder setId(int id) {
            this.id = id;
            return this;
        }

        @Override
        public UserBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        @Override
        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        @Override
        public UserBuilder setPhone(String phone_number) {
            this.phone_number = phone_number;
            return this;
        }


        @Override
        public UserBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        @Override
        public UserBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        @Override
        public User build(){
            return new User(this);
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
