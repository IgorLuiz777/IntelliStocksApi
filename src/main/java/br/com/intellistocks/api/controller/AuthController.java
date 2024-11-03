package br.com.intellistocks.api.controller;

import br.com.intellistocks.api.Auth.AuthService;
import br.com.intellistocks.api.Auth.Credentionals;
import br.com.intellistocks.api.Auth.Token;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Auth", description = "Endpoints para login")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Token login (@RequestBody Credentionals credentials){
        return authService.login(credentials);
    }
}