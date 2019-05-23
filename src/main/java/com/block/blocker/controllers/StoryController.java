package com.block.blocker.controllers;


import com.block.blocker.models.Chapter;
import com.block.blocker.models.Composition;
import com.block.blocker.repositories.ChapterRepository;
import com.block.blocker.repositories.CompositionRepository;
import com.block.blocker.repositories.UserReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class StoryController {

    @Autowired
    private
    UserReposiroty uRep;

    @Autowired
    ChapterRepository cRep;

    @Autowired
    CompositionRepository compRep;



    @PostMapping("/story")
    public String story(Map<String, Object> model, @RequestParam(name = "req", required = false, defaultValue = "") String view){
        Chapter chapter = new Chapter();
        chapter.setText(view);
        cRep.save(chapter);
        model.put("view", view);
        return "redirect:/newStory";
    }

    @GetMapping("/newStory")
    public String newStory(Map<String, Object> model){
        Iterable<Chapter> chaps = cRep.findAll();
        model.put("chaps", chaps);
        return "newStory";
    }
    @GetMapping("/createComposition")
    public String NewComposition(Map<String, Object> model){
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        model.put("currentUser", currentUser.getName());
        return "CreateComposition";
    }
    @PostMapping("/createNewStory")
    public String addNewComposition(Map<String, Object> model,
                                    @RequestParam(name = "title", required = false, defaultValue = "") String title,
                                    @RequestParam(name = "description", required = false, defaultValue = "") String description)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Composition composition = new Composition(uRep.findByUsername(authentication.getName()));
        composition.setDescription(description);
        composition.setTitle(title);
        compRep.save(composition);

        return "redirect:/userPage";
    }

}
