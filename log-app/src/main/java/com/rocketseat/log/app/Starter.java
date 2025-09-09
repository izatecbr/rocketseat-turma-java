package com.rocketseat.log.app;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class Starter implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(Starter.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("SPRING RODANDO APOS O SEU CARREGAMENTO STANDALONE");
        logger.trace("TRACE: Isso e uma mensagem de TRACE");
        logger.debug("DEBUG: Isso e uma mensagem de DEBUG");

        logger.info("INFO: Aplicacao iniciada com sucesso!");

        logger.warn("WARN: Isso e um aviso WARN");
        logger.error("ERRO: Isso e uma mensagem de ERRO");

        try {
            int resultado = 10 / 0;
        } catch (ArithmeticException ex) {
            logger.error("Erro ao tentar dividir por zero");
            //logger.error("Erro ao tentar dividir por zero", ex);
        }
    }
}
