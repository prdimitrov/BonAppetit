package com.bonappetit.controller;

import com.bonappetit.model.viewModels.RecipeViewModel;
import com.bonappetit.service.RecipeService;
import com.bonappetit.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final RecipeService recipeService;

    public HomeController(CurrentUser currentUser, RecipeService recipeService) {
        this.currentUser = currentUser;
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String index() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!currentUser.isLoggedIn()) {
            //If user is not logged in
            return "redirect:/";
        }
        //If user is logged in
        List<RecipeViewModel> recipiesList = recipeService.getAllRecipes();
        return null;
    }
}
