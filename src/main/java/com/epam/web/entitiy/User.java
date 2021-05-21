package com.epam.web.entitiy;

import java.math.BigDecimal;

public class User implements Identifiable {

    private final long id;
    private final String login;
    private final String password;
    private final BigDecimal balance;
    private final UserRole role;

    public User(long id, String login, String password, BigDecimal balance, UserRole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.balance = balance;
        this.role = role;
    }

    @Override
    public long getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role.toString();
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

}
