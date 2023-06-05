package com.study.server.classes;

import lombok.Data;

@Data
public class AddMemberRequest {
    private String email;
    private String password;
}
