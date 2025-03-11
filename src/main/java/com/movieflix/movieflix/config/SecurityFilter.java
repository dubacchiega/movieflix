package com.movieflix.movieflix.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (Strings.isNotEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());

            Optional<JWTUserData> optJwtUserData = tokenService.verifyToken(token);
            if (optJwtUserData.isPresent()){
                JWTUserData jwtUserData =  optJwtUserData.get();

                // colocando o objeto inteiro, isso me permite pegar o usuário autenticado em qualquer lugar da aplicação. Tendo acesso ao id, nome e email
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(jwtUserData, null, null);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response); // continua a cadeia de filtros. Necessario para continuar a execução da requisição.
        }else {
            // se não tiver o token, continua a cadeia de filtros, porem sem autenticação. Isso ira barrar a requisição em qualquer lugar que precise de autenticação.
            filterChain.doFilter(request, response);
        }
    }
}
