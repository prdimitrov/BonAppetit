package com.bonappetit.model.bindingModels;

import com.bonappetit.model.entity.User;
import com.bonappetit.model.enums.CategoryName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddRecipeModel {

    @NotNull
    @Size(min = 2, max = 40, message = "Name length must be between 2 and 40 characters!")
    private String name;

    @NotNull
    @Size(min = 2, max = 80, message = "Ingredients length must be between 2 and 80 characters!")
    private String ingredients;

    @NotNull(message = "You must select a category!")
    private CategoryName category;


    private User addedBy;

    public AddRecipeModel() {
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

    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }
}
