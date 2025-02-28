package com.movieflix.movieflix.category.controller;

import com.movieflix.movieflix.category.entity.Category;
import com.movieflix.movieflix.category.mapper.CategoryMapper;
import com.movieflix.movieflix.category.request.CategoryRequest;
import com.movieflix.movieflix.category.response.CategoryResponse;
import com.movieflix.movieflix.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor // serve como injeção de dependencias
public class CategoryController {

//    @Autowired
    private final CategoryService categoryService;

    // O @RequiredArgsConstructor faz isso por baixo dos panos
    /*public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }*/

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
         List<CategoryResponse> categories = categoryService.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        return ResponseEntity.ok(categories);
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest request){
        Category newCategory = CategoryMapper.toCategory(request);
        Category savedCategory = categoryService.saveCategory(newCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponse(savedCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getByCategoryId(@PathVariable Long id){
        return categoryService.findById(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category))) // caso o optional exista
                .orElse(ResponseEntity.notFound().build()); // caso o optional seja null
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByCategoryId(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
