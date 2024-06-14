package com.bonappetit.model.viewModels;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.User;

public class RecipeViewModel {

    private Long id;

    private String name;

    private String ingredients;

    private Category category;

    private User addedBy;

    public RecipeViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }
}
