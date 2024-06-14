package com.bonappetit.service;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.enums.CategoryName;
import com.bonappetit.repo.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    private void categoriesInitialization() {
        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        Category category = new Category();
                        category.setName(categoryName);

                        switch (categoryName) {
                            case MAIN_DISH:
                                category.setDescription("Heart of the meal, substantial and satisfying; main dish delights taste buds.");
                                break;
                            case DESSERT:
                                category.setDescription("Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy.");
                                break;
                            case COCKTAIL:
                                category.setDescription("Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass.");
                                break;
                        }
                        categoryRepository.save(category);
                    });
        }
    }
}
