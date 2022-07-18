package com.mcb.mcsharesproject.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serial;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = false)
public class UserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5408905512118037537L;

    private String name;
    private String username;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
