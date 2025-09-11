package com.rocketseat.mylogapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    public void save(){
        //System.out.println("Salvando um pedido");
        log.debug("DEBUG->Salvando um pedido");
    }
}
