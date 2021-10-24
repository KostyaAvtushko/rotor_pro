package com.rotor.serwingwebcontent.service;

import com.rotor.serwingwebcontent.entity.User;
import com.rotor.serwingwebcontent.repositories.UserRepo;
import com.rotor.serwingwebcontent.roles.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService  implements UserDetailsService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        return user;
    }

    @PostMapping(path="/add") // Map ONLY POST Requests
    public boolean addUser (User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setRolesSet(Collections.singleton(Roles.USER));
        user.setActivationcode(UUID.randomUUID().toString());
        user.setActive(true);
        userRepo.save(user);

        if(!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Hello, %s \n" +
                            "Welcome to Rotor-Pro. Please, visit next link: https://rotor-test.herokuapp.com/activate/%s",
              user.getUsername(), user.getActivationcode()
            );

               mailSender.send(user.getEmail(), "Activation code", message);

        }

        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationcode(code);

        if(user == null){
            return false;
        }
        user.setActive(true);
        user.setActivationcode(null);
        userRepo.save(user);
        return true;
    }
}
