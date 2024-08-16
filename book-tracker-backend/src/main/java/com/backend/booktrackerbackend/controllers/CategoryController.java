package com.backend.booktrackerbackend.controllers;

import com.backend.booktrackerbackend.controllers.requests.CreateCategoryRequest;
import com.backend.booktrackerbackend.controllers.responses.CategoryResponse;
import com.backend.booktrackerbackend.models.Category;
import com.backend.booktrackerbackend.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryServices categoryServices;

    @Autowired
    public CategoryController(CategoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        try {
            var categories = categoryServices.findAll().stream()
                    .map(CategoryResponse::from)
                    .toList();

            return categories.isEmpty() ?
                    ResponseEntity.noContent().build() :
                    ResponseEntity.ok(categories);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Integer categoryId) {
        try {
            Optional<Category> category = categoryServices.findById(categoryId);

            return category.map(CategoryResponse::from)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.noContent().build());

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Category> createNewCategory(@RequestBody CreateCategoryRequest newCategory) {
        try {
            Category created = categoryServices.saveCategory(newCategory.toEntity());
            URI ubi = URI.create(String.format("/categories/%d", created.getId()));

            return ResponseEntity.created(ubi).body(created);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{deleteId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Integer deleteId) {
        try {
            categoryServices.deleteCategoryById(deleteId);

            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
