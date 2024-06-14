package com.bonappetit.service;

import com.bonappetit.model.bindingModels.AddRecipeModel;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.model.viewModels.RecipeViewModel;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final CategoryService categoryService;

    public RecipeService(RecipeRepository recipeRepository, ModelMapper modelMapper, CurrentUser currentUser, UserRepository userRepository, CategoryService categoryService) {
        this.recipeRepository = recipeRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.categoryService = categoryService;
    }

    public List<RecipeViewModel> getAllRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(recipe -> modelMapper.map(recipe, RecipeViewModel.class))
                .collect(Collectors.toList());
    }

    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.getById(recipeId);
    }

    public void addRecipe(AddRecipeModel recipeModel) {
        Recipe recipe = modelMapper.map(recipeModel, Recipe.class);
        recipe.setCategory(categoryService.getByCategoryName(recipeModel.getCategory()));
        User user = userRepository.findByUsername(currentUser.getUsername()).orElseThrow();
        recipe.setAddedBy(user);

        recipeRepository.saveAndFlush(recipe);
    }

    public void removeRecipe(Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }
}
