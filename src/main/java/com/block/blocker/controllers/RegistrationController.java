package com.block.blocker.controllers;

import com.block.blocker.models.Role;
import com.block.blocker.models.User;
import com.block.blocker.repositories.UserReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.soap.SOAPBinding;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserReposiroty userRepository;
    private final String reistrationPage = "/registration";

    @GetMapping(reistrationPage)

    public String registration() {

        return "registration";

    }
    @PostMapping(reistrationPage)
    public String addUser(User user, Map<String, Object> model){
        User userName = userRepository.findByUsername(user.getUsername());
        User userEmail = userRepository.findByUsername(user.getEmail());
        if(userName != null && userEmail != null){
            model.put("message", "Username exists or Email exists!");
            return "registration";
        }
//   User Generator
//        for(Integer i = 0; i<14; i++){
//
//                        User us = new User("User"+i.toString(),"123",i.toString()+"post@mail.com");
//                        us.setRoles(Collections.singleton(Role.USER));
//                        us.setBlock(true);
//                        userRepository.save(us);
//        }

            user.setBlock(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);

        return "redirect:/login";
    }
}
