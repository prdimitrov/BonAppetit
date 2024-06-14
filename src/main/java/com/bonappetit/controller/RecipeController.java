package com.bonappetit.controller;

import com.bonappetit.model.bindingModels.AddRecipeModel;
import com.bonappetit.service.RecipeService;
import com.bonappetit.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final CurrentUser currentUser;

    public RecipeController(RecipeService recipeService, CurrentUser currentUser) {
        this.recipeService = recipeService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("recipeModel")
    public AddRecipeModel recipeModel() {
        return new AddRecipeModel();
    }

    @GetMapping("/add")
    public String getAddForm() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "recipe-add";
    }

    @PostMapping("/add")
    public String addRecipe(@Valid AddRecipeModel recipeModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        //If user is not logged in.
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeModel", recipeModel);
            redirectAttributes
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.recipeModel",
                            bindingResult);
            //Redirect to the /recipes/add, if there are errors!
            return "redirect:/recipes/add";
        }
        recipeService.addRecipe(recipeModel);
        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String removeRecipe(@PathVariable Long recipeId) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        recipeService.removeRecipe(recipeId);
        return "redirect:/home";
    }
}
