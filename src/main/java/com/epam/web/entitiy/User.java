package com.epam.web.entitiy;

import java.math.BigDecimal;

public class User implements Identifiable {

    private final long id;
    private final String login;
    private final String password;
    private final BigDecimal balance;
    private final UserRole role;
    private final boolean isBlocked;

    public User(long id, String login, String password, BigDecimal balance, UserRole role, boolean isBlocked) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.balance = balance;
        this.role = role;
        this.isBlocked = isBlocked;
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
        return this.role.name();
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public boolean getIsBlocked() {
        return this.isBlocked;
    }
}
