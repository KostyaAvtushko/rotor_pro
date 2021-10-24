package com.rotor.serwingwebcontent.Controllers;


import com.rotor.serwingwebcontent.entity.User;
import com.rotor.serwingwebcontent.repositories.UserRepo;
import com.rotor.serwingwebcontent.roles.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/user")
    public String userList(Model model){
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("/user/{user}")
    public String userEditForm(@PathVariable Long user, Model model) {
        User userFromDb = userRepo.findById(user);
        model.addAttribute("user", userFromDb);
        model.addAttribute("roles", Roles.values());

        return "userEdit";
    }

    @PostMapping("/user")
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") Long userId
    ) {
        User user = userRepo.findById(userId);
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Roles.values())
                .map(Roles::name)
                .collect(Collectors.toSet());

        user.getRolesSet().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRolesSet().add(Roles.valueOf(key));
            }
        }

        userRepo.save(user);

        return "redirect:/user";
    }
}
