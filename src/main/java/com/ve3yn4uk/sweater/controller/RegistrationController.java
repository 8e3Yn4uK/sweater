package com.ve3yn4uk.sweater.controller;

import com.ve3yn4uk.sweater.domain.Role;
import com.ve3yn4uk.sweater.domain.User;
import com.ve3yn4uk.sweater.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    private UserRepo repo;

    @Autowired
    public RegistrationController(UserRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/registration")
    public String registration(){
    return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = repo.findByUsername(user.getUsername());

        if (userFromDb != null){
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        repo.save(user);
        return "redirect:/login";
    }
}
