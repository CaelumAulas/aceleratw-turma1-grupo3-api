package com.br.latavelhaapi.service;

import com.br.latavelhaapi.model.User;
import com.br.latavelhaapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //ADD
    public Object add(User user){
        return userRepository.save(user);
    }

    //List
    public List<User> list(){
        return userRepository.findAll();
    }

    //Deletar
    public void delete(long id){
        User user = userRepository.findByID(id);
        if(user != null){
            userRepository.delete(user);
        }
    }

    //Atualizar
    public User update(User user){
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(long id) {
        return userRepository.findByID(id);
    }
}
