package com.rocketseat.mylogapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("caixinhas")
public class CaixinhaController {
    @GetMapping("saldo")
    public Caixinha getSaldo(){
        Caixinha caixinha = new Caixinha();
        caixinha.setId(1);
        caixinha.setNome("Ferias na Europa");
        caixinha.setSaldo(123.0);
        return caixinha;
    }
}
