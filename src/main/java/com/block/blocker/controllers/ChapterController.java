package com.block.blocker.controllers;


import com.block.blocker.models.Chapter;
import com.block.blocker.models.Composition;
import com.block.blocker.repositories.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ChapterController {

    @Autowired
    ChapterRepository cRep;


    @GetMapping("/createChapter/{composition}")
    public String writeChapter(@PathVariable Composition composition, Map<String, Object> model){



        model.put("composition", composition.getId());
        return "chapter/newChapter";
    }

    @PostMapping("/addChapter/{composition}")
    public String addChapter(@PathVariable Composition composition,
                               @RequestParam(name = "chapterText", required = false, defaultValue = "" ) String chapterText,
                               @RequestParam(name = "title", required = false, defaultValue = "") String title){

        Chapter chapter = new Chapter(title, chapterText,composition);
        cRep.save(chapter);
        return "redirect:/userPage";

    }

    @GetMapping("/{composition}/chapters")
    public String showChapters(@PathVariable Composition composition, Map<String, Object> model){

        Iterable<Chapter> chapters = cRep.findAllByCompositionOrderByIdDesc(composition);
        model.put("chapter", chapters);

        return "chapter/chapters";

    }
    @GetMapping("/editChapter/{chapter}")
    public String editingChapter(@PathVariable Chapter chapter, Map<String, Object> model){

        model.put("chapter", chapter);
        model.put("id",chapter.getId());
        return "chapter/editChapter";
    }

    @PostMapping("/editChapter/{chapter}")
    public String editChapter(@PathVariable Chapter chapter, Map<String, Object> model,
                              @RequestParam(name = "chapterText", required = false, defaultValue = "" ) String chapterText,
                              @RequestParam(name = "title", required = false, defaultValue = "") String title){

        chapter.setText(chapterText);
        chapter.setChapterTitle(title);
        cRep.save(chapter);


        return "redirect:/"+chapter.getComposition().getId()+"/chapters";
    }

    @GetMapping("/deleteChapter/{chapter}")
    public String deleteChapter(@PathVariable Chapter chapter, Map<String, Object> model){
        cRep.delete(chapter);
        return "redirect:/"+chapter.getComposition().getId()+"/chapters";
    }


}
