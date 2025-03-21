package com.movieflix.movieflix.documentation;
import com.movieflix.movieflix.user.request.LoginRequest;
import com.movieflix.movieflix.user.request.LoginResponse;
import com.movieflix.movieflix.user.request.UserRequest;
import com.movieflix.movieflix.user.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth", description = "Autenticação")
public interface AuthControllerDoc {

    @PostMapping("/register")
    @Operation(summary = "Cria um usuário", description = "Essa operação salva um novo registro com as informações do usuário.")
    @ApiResponse(responseCode = "201", description = "Sucesso ao criar um usuário",
            content = @Content(schema = @Schema(implementation = UserResponse.class)))
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) ;


    @PostMapping("/login")
    @Operation(summary = "Autentica um usuário", description = "Essa operação autentica um usuário.")
    @ApiResponse(responseCode = "200", description = "Sucesso ao autenticar um usuário",
            content = @Content(schema = @Schema(implementation = LoginResponse.class)))
    @ApiResponse(responseCode = "400", description = "Usuário ou senha inválidos")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) ;
}
