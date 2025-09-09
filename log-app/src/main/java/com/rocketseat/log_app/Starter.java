package com.rocketseat.log_app;

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

        logger.info("🚀 Aplicação iniciada com sucesso!");
        logger.debug("🔍 Isso é uma mensagem de DEBUG");
        logger.warn("⚠️ Isso é um aviso (WARN)");
        logger.error("💥 Isso é uma mensagem de ERRO");

        try {
            int resultado = 10 / 0;
        } catch (ArithmeticException ex) {
            logger.error("Erro ao tentar dividir por zero");
            //logger.error("Erro ao tentar dividir por zero", ex);
        }
    }
}
