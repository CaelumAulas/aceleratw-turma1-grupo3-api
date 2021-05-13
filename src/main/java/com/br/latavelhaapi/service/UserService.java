package com.br.latavelhaapi.service;

import com.br.latavelhaapi.model.User;
import com.br.latavelhaapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Object add(User user){
        return userRepository.save(user);
    }

    public List<User> list(){
        return userRepository.findAll();
    }

    public void delete(long id){
        User user = userRepository.findByID(id);
        if(user != null){
            userRepository.delete(user);
        }
    }

    public User update(User user){
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
