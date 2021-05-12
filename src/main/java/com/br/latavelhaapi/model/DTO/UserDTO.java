package com.br.latavelhaapi.model.DTO;

import com.br.latavelhaapi.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {

    private String name;
    private String email;

    public UserDTO(User user){
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static List<UserDTO> convert(List<User> users){
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }
}
