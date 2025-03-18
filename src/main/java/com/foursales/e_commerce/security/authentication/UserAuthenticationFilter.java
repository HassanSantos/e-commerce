package com.foursales.e_commerce.security.authentication;


import com.foursales.e_commerce.infrastructure.service.repository.UserRepository;
import com.foursales.e_commerce.infrastructure.service.repository.entity.UserEntity;
import com.foursales.e_commerce.security.config.SecurityConfiguration;
import com.foursales.e_commerce.security.userdetails.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService; // Service que definimos anteriormente

    @Autowired
    private UserRepository userRepository; // Repository que definimos anteriormente

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (checkIfEndpointIsNotPublic(request)) {
                String token = recoveryToken(request); // Recupera o token do cabeçalho Authorization da requisição
                if (token != null) {
                    String subject = jwtTokenService.getSubjectFromToken(token); // Obtém o assunto (neste caso, o nome de usuário) do token
                    UserEntity userEntity = userRepository.findByEmail(subject).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));
                    UserDetailsImpl userDetails = new UserDetailsImpl(userEntity); // Cria um UserDetails com o usuário encontrado

                    // Cria um objeto de autenticação do Spring Security
                    Authentication authentication =
                            new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

                    // Define o objeto de autenticação no contexto de segurança do Spring Security
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new TokenNotFoundException("O token está ausente.");
                }
                filterChain.doFilter(request, response); // Continua o processamento da requisição
            }

        } catch (CustomAuthenticationException e) {
            handleAuthenticationException(response, e); // Lida com a exceção personalizada e envia o código HTTP adequado
        }
    }

    private void handleAuthenticationException(HttpServletResponse response, CustomAuthenticationException e) throws IOException {
        response.setStatus(e.getStatusCode());
        response.getWriter().write(e.getMessage()); // Opcional: escreva a mensagem de erro no corpo da resposta
    }


    // Recupera o token do cabeçalho Authorization da requisição
    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    // Verifica se o endpoint requer autenticação antes de processar a requisição
    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return !Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(requestURI);
    }

}
