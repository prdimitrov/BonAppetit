package com.bonappetit.controller;

import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.model.enums.CategoryName;
import com.bonappetit.model.viewModels.RecipeViewModel;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import com.bonappetit.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final RecipeService recipeService;
    private final UserRepository userRepository;
    private final UserService userService;

    public HomeController(CurrentUser currentUser, RecipeService recipeService, UserRepository userRepository, UserService userService) {
        this.currentUser = currentUser;
        this.recipeService = recipeService;
        this.userRepository = userRepository;
        this.userService = userService;
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
        List<RecipeViewModel> recipesList = recipeService.getAllRecipes();

        //Main dishes list
        List<RecipeViewModel> dishes = recipesList
                .stream()
                .filter(recipe -> recipe.getCategory().getName() == CategoryName.MAIN_DISH)
                .collect(Collectors.toList());

        //Desserts list
        List<RecipeViewModel> desserts = recipesList
                .stream()
                .filter(recipe -> recipe.getCategory().getName() == CategoryName.DESSERT)
                .collect(Collectors.toList());

        //Cocktails list
        List<RecipeViewModel> cocktails = recipesList
                .stream()
                .filter(recipe -> recipe.getCategory().getName() == CategoryName.COCKTAIL)
                .collect(Collectors.toList());

        //Recipe attributes
        model.addAttribute("dishes", dishes);
        model.addAttribute("desserts", desserts);
        model.addAttribute("cocktails", cocktails);

        //Count of the different recipes categories
        model.addAttribute("dishesCount", dishes.size());
        model.addAttribute("dessertsCount", desserts.size());
        model.addAttribute("cocktailsCount", cocktails.size());

        //User's favourite recipies and their count!
        List<Recipe> favouriteRecipes = userRepository.findUserFavouriteRecipes(currentUser.getUsername());
        model.addAttribute("favouriteRecipes", favouriteRecipes);

        int favouritesCount = favouriteRecipes.size();
        model.addAttribute("favouritesCount", favouritesCount);

        return "home";
    }

    @Transactional
    @GetMapping("/users/add-favourite")
    private String addFavourite(@RequestParam Long recipeId) {
        //If user is not logged in!
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        Optional<User> optUser = userRepository.findByUsername(currentUser.getUsername());

        if (optUser.isPresent()) {
            Set<Recipe> favouriteRecipes = optUser.get().getFavouriteRecipes();

            Recipe recipeById = recipeService.getRecipeById(recipeId);

            favouriteRecipes.add(recipeById);

            userService.updateFavouriteRecipes(currentUser.getUsername(), favouriteRecipes);
        }

        return "redirect:/home";
    }
}
