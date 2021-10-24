package com.rotor.serwingwebcontent.Controllers;

import com.rotor.serwingwebcontent.entity.User;
import com.rotor.serwingwebcontent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegController {
    @Autowired
    private UserService userService;

    @PostMapping(path="/add")
    public String addNewUser (User user, Map<String, Object> model) {


        if (!userService.addUser(user)) {
            model.put("message", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping(path = "/add")
    public  String register(){
        return "registration";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);

        if(isActivated){
            model.addAttribute("message", "User activated");
        }else{
            model.addAttribute("message", "Error");
        }

        return "login";
    }

}
