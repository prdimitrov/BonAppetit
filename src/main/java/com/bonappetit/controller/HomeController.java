package com.bonappetit.controller;

import com.bonappetit.service.RecipeService;
import com.bonappetit.util.CurrentUser;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final RecipeService recipeService;

    public HomeController(CurrentUser currentUser, RecipeService recipeService) {
        this.currentUser = currentUser;
        this.recipeService = recipeService;
    }
}
