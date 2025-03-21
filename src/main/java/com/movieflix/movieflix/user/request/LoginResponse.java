package com.movieflix.movieflix.user.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponse(
        @Schema(type = "string", description = "Token de autenticação")
        String token) {
}
