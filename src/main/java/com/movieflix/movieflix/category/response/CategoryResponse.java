package com.movieflix.movieflix.category.response;

import lombok.Builder;

@Builder
public record CategoryResponse(Long id, String name) {
}
