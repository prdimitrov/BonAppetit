package com.bonappetit.service;

import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.viewModels.RecipeViewModel;
import com.bonappetit.repo.RecipeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final ModelMapper modelMapper;

    public RecipeService(RecipeRepository recipeRepository, ModelMapper modelMapper) {
        this.recipeRepository = recipeRepository;
        this.modelMapper = modelMapper;
    }

    public List<RecipeViewModel> getAllRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(recipe -> modelMapper.map(recipe, RecipeViewModel.class))
                .collect(Collectors.toList());
    }
}
