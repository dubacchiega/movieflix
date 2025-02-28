package com.movieflix.movieflix.category.mapper;

import com.movieflix.movieflix.category.entity.Category;
import com.movieflix.movieflix.category.request.CategoryRequest;
import com.movieflix.movieflix.category.response.CategoryResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest categoryRequest){
        return Category.builder()
                .name(categoryRequest.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
