package com.example.controller;

import com.example.model.Director;
import com.example.repository.DirectorsRepository;
import com.example.services.DirectorService;
import com.example.services.implementation.DirectorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/director")
public class DirectorController {

    DirectorService directorService;

    @Autowired
    public void setDirectorService(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public String showDirectorsPage(Model model) {
        model.addAttribute("directors", directorService.getDirectors());
        return "directors-page";
    }

    @GetMapping("/add")
    public String showAddDirectorPage(Model model) {
        Director director = new Director();
        model.addAttribute("director", director);
        return "add_director";
    }

    @PostMapping("/add")
    public String showDirectorsPageOnAddDirector(@ModelAttribute("director") Director director) {
        directorService.createDirector(director);
        return "redirect:/director";
    }

    @GetMapping("/update")
    public String showUpdateDirectorPage(Model model, @RequestParam(value = "fullname") String fullname) {
        Director director;
        try {
            director = directorService.getDirectorByFullName(fullname);
        } catch (DirectorNotFoundException e) {
            return "director-not-found";
        }
        model.addAttribute("director", director);
        return "update-director";
    }

    @PostMapping("/update")
    public String showUpdateDirectorPageOnUpdateDirector(@ModelAttribute("director") Director director) {
        directorService.updateDirector(director);
        return "redirect:/director";
    }


    @GetMapping("/delete")
    public String deleteDirector(Model model, @RequestParam(value = "fullname") String fullname) {
        System.out.println("In delete method");
        Director director;
        try {
            director = directorService.getDirectorByFullName(fullname);
            directorService.deleteDirectorByFullName(director.getFullName());

        } catch (DirectorNotFoundException e) {
            return "director-not-found";
        }
        return "redirect:/director";
    }

/*    @PostMapping("/delete")
    public String showFindDirectorPageOnUpdateDirector(@ModelAttribute("director") Director director) {
        directorService.deleteDirectorByFullName(director.getFullName());
        return "redirect:/director";
    }*/


}
