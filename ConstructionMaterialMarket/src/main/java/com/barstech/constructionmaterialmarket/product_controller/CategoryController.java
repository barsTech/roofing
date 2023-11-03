package com.barstech.constructionmaterialmarket.product_controller;

import com.barstech.constructionmaterialmarket.product_model.Category;
import com.barstech.constructionmaterialmarket.product_service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RestController("/api")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> listOfCategory() {
        List<Category> list = categoryService.listOfCategory();
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveCategory(@RequestBody Category category) {
        if (category != null) {
           categoryService.saveCategory(category);
            return new ResponseEntity<>("Category is Crated...", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Bad Request.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/categories/{id}/update")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody Category updateCategory) {
       Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return new ResponseEntity<>("Category not Found", HttpStatus.NOT_FOUND);
        } else {
            category.setName(updateCategory.getName());
            // Diğer alanları güncelleyin
           categoryService.saveCategory(category);
            return new ResponseEntity<>("Category is Updated", HttpStatus.OK);
        }
    }

    @GetMapping("/categories/{id}/found")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
       Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
    }

    @GetMapping("/categories/{id}/delete")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
       Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return new ResponseEntity<>("Category Not Found",HttpStatus.NOT_FOUND);
        } else {
           categoryService.deleteCategory(category);
            return new ResponseEntity<>("Category is Deleted...", HttpStatus.OK);
        }
    }
}
