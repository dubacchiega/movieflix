package com.movieflix.movieflix.streaming.response;

import lombok.Builder;

@Builder
public record StreamingResponse(Long id, String name) {
}
