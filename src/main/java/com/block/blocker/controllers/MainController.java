package com.block.blocker.controllers;


import com.block.blocker.models.Chapter;
import com.block.blocker.models.Composition;
import com.block.blocker.models.User;
import com.block.blocker.repositories.ChapterRepository;
import com.block.blocker.repositories.CompositionRepository;
import com.block.blocker.repositories.UserReposiroty;
import com.sun.deploy.util.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.*;

@Controller
public class MainController {



    @GetMapping()
    public String userManager(Map<String, Object> model) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();



        model.put("currentUser", currentUser.getName());
        return "main";
    }






    // Manager




    private void delete(User userFromDB) {
//        uRep.delete(userFromDB);

    }

    private boolean currentUserIsDeleted(User userFromDB, Authentication currentUser){
        return currentUser.getName().equals(userFromDB.getUsername());
    }

    private void blockUnblock(User userFromDB) {
        if (userFromDB.getBlock()) {
            userFromDB.setBlock(false);
        } else {
            userFromDB.setBlock(true);
        }
//        uRep.save(userFromDB);

    }

    private boolean currentUserIsBanned(User userFromDB, Authentication currentUser){
        return currentUser.getName().equals(userFromDB.getUsername());
    }

    private void blockUnblockUsers(User userFromDB, Authentication currentUser){
        blockUnblock(userFromDB);
        if (currentUserIsBanned(userFromDB, currentUser)){

            currentUser.setAuthenticated(false);
        }
    }

    private void deleteUsers(User userFromDB, Authentication currentUser){
        delete(userFromDB);
        if (currentUserIsDeleted(userFromDB, currentUser)){
            currentUser.setAuthenticated(false);
        }
    }

    private void deleteAndChangeUserStatus(String event, User userFromDB, Authentication currentUser){
        if (event.equals("Actеive/Disactive")) {
            blockUnblockUsers(userFromDB, currentUser);
        }
        else if (event.equals("Delete")) {
            deleteUsers(userFromDB, currentUser);
        }
    }

}

