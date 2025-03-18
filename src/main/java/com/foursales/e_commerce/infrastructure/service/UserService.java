package com.foursales.e_commerce.infrastructure.service;


import com.foursales.e_commerce.dto.CreateUserDto;
import com.foursales.e_commerce.dto.LoginUserDto;
import com.foursales.e_commerce.dto.RecoveryJwtTokenDto;
import com.foursales.e_commerce.infrastructure.service.repository.UserRepository;
import com.foursales.e_commerce.infrastructure.service.repository.entity.Role;
import com.foursales.e_commerce.infrastructure.service.repository.entity.User;
import com.foursales.e_commerce.security.authentication.JwtTokenService;
import com.foursales.e_commerce.security.config.SecurityConfiguration;
import com.foursales.e_commerce.security.userdetails.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    // Método responsável por autenticar um usuário e retornar um token JWT
    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        try {
            // Cria um objeto de autenticação com o email e a senha do usuário
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

                Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            // Obtém o objeto UserDetails do usuário autenticado
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            // Gera um token JWT para o usuário autenticado
            return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // Método responsável por criar um usuário
    public void createUser(CreateUserDto createUserDto) {

        // Cria um novo usuário com os dados fornecidos
        User newUser = User.builder()
                .name("hassan")
                .email(createUserDto.email())
                // Codifica a senha do usuário com o algoritmo bcrypt
                .password(securityConfiguration.passwordEncoder().encode(createUserDto.password()))
                // Atribui ao usuário uma permissão específica
                .roles(List.of(Role.builder().name(createUserDto.role()).build()))
                .build();

        // Salva o novo usuário no banco de dados

        try {

            userRepository.save(newUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
