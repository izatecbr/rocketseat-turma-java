package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String idTerminal;
    private Integer numeroTransacao;
    private LocalDate dataMovimento;
    private Double valorVenda;
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;
}
