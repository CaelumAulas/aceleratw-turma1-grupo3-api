package com.br.latavelhaapi.repository;

import com.br.latavelhaapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByID(Long id);

    User findByEmail(String email);

    void delete(User user);

    Optional<User> save(Optional<User> user);
}
