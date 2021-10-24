package com.rotor.serwingwebcontent.repositories;

import com.rotor.serwingwebcontent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findById(Long id);
    User findByActivationcode(String code);
}