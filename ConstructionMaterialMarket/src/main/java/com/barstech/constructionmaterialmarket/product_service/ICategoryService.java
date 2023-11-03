package com.barstech.constructionmaterialmarket.product_service;

import com.barstech.constructionmaterialmarket.product_model.Category;
import java.util.List;

public interface ICategoryService {

    List<Category> listOfCategory();

    Category getCategoryById(Long id);

    void saveCategory(Category category);

    void deleteCategory(Category category);
}
