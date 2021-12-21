package com.lab2.users.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class UserItem {
    private String login;
    private String password;
    private Boolean moderator;
    private Integer status;
}
