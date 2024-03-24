package com.atharvapuranik.imapp.controller;


import com.atharvapuranik.imapp.config.jwtService;
import com.atharvapuranik.imapp.repository.UserRepository;
import com.atharvapuranik.imapp.user.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class authService {

    private final UserRepository repository;
    private final jwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final Logger LOGGER = LoggerFactory.getLogger(authService.class);


    public AuthenticationResponse register(
            registerRequest request
    ){
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .role(request.getRole())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        User savedUser = repository.save(user);
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .jwtToken(token)
                .build();
    }

    public AuthenticationResponse authenticate(
            authenticationRequest request
    ){
        try{

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e){
            LOGGER.info(String.format("Error:%s",e));
        }
        User user = repository.findByEmail(request.getEmail())
                        .orElseThrow();

        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .jwtToken(token)
                .build();
    }
}
