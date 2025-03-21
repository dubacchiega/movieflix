package com.movieflix.movieflix.streaming.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record StreamingRequest(
        @Schema(type = "string", description = "Nome do serviço de streaming")
        @NotEmpty(message = "Nome do serviço de streaming é obrigatório.")
        String name) {
}
