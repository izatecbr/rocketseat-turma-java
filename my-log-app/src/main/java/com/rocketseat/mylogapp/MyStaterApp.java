package com.rocketseat.mylogapp;

import com.rocketseat.mylogapp.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyStaterApp implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(MyStaterApp.class);
    @Autowired
    private OrderService orderService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.trace("Esta mensagem serve para o dev vasculhar o codigo");
        //log.info("Aplicacao iniciada com sucesso!");
        //log.warn("Alerta, este codigo foi feito com Chat GPT");
        //log.error("Cuidado em dependender plenamente de todas as abordagens sugeridas");
        orderService.save();
    }
}
