package com.rocketseat.log.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    public boolean autenticar(String email, String senha) {
        logger.info("Iniciando autenticacao para o usuario: {}", email);

        if (email == null || senha == null) {
            logger.warn("Email ou senha estão nulos.");
            return false;
        }

        logger.trace("Verificando credenciais...");

        if ("admin@admin".equals(email) && "Sup3rP@ss".equals(senha)) {
            logger.debug("Usuario autenticado com sucesso.");
            return true;
        }else
            logger.warn("Credencial invalida");


        logger.debug("Falha na autenticacao para o usuario: {}", email);
        logger.debug("Senha recebida: {}", senha);
        return false;
    }
}
