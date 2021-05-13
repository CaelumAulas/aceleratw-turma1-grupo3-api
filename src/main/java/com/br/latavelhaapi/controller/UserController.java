package com.br.latavelhaapi.controller;

import com.br.latavelhaapi.model.DTO.UserForm;
import com.br.latavelhaapi.model.User;
import com.br.latavelhaapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User add(@RequestBody @Valid UserForm userForm){
        User user = userForm.convert(userService);
        userService.add(user);
        return user;
    }

    @GetMapping
    public List<User> list(){
        List<User> users = userService.list();
        return users;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable long id, @RequestBody @Valid UserForm userForm){
        User user = userForm.convert(userService);
        return userService.update(user);
    }
}
