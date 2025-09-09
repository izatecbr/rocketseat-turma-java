package com.rocketseat.log.app.controller;

import com.rocketseat.log.app.model.Login;
import com.rocketseat.log.app.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    //@Autowired
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public String login(@RequestBody Login login) {
        logger.info("Requisicao de login recebida para o usuario: {}", login.getEmail());
        logger.trace("A senha é: {}", login.getSenha());
        boolean sucesso = loginService.autenticar(login.getEmail(), login.getSenha());
        return sucesso ? "Login bem-sucedido" : "Credenciais inválidas";
    }
}
