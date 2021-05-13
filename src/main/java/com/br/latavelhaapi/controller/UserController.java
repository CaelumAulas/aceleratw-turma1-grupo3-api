package com.br.latavelhaapi.controller;

import com.br.latavelhaapi.model.DTO.UserForm;
import com.br.latavelhaapi.model.User;
import com.br.latavelhaapi.payload.Response;
import com.br.latavelhaapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> list() {
        List<User> users = userService.list();
        if(users == null){
            return new ResponseEntity<>(
                    new Response(false, "Not found users"),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        Optional<User> findUser = userService.findById(id);
        if(findUser.isPresent()){
            userService.delete(id);
            return new ResponseEntity<User>(findUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new Response(false, "Not found user with id:" + id),
                HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable("id") Long id) {
        Optional<User> findUser = userService.findById(id);
        if(findUser.isPresent()){
            user.setID(findUser.get().getID());
            userService.update(user);
            return new ResponseEntity<User>(findUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new Response(false, "Not found user with id: " + id),
                HttpStatus.NOT_FOUND);
    }
}
