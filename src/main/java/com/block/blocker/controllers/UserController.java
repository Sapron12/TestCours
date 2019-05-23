package com.block.blocker.controllers;

import com.block.blocker.models.Composition;
import com.block.blocker.models.User;
import com.block.blocker.repositories.CompositionRepository;
import com.block.blocker.repositories.UserReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
@Controller
public class UserController {

    @Autowired
    UserReposiroty uRep;
    @Autowired
    CompositionRepository compRep;


    @GetMapping("/userPage")
    public String userPage(Map<String, Object> model){
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        User user = uRep.findByUsername(currentUser.getName());
        Long userId =  user.getId();
        Iterable<Composition> d = compRep.findAllByAuthor(user);
        model.put("comps", d);
        return "userPage";
    }

}
