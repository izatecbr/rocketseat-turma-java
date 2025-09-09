package com.rocketseat.log_app;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Starter implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("SPRING RODANDO APOS O SEU CARREGAMENTO STANDALONE");
    }
}
