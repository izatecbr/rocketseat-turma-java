package com.example.demo.model;

import java.util.Arrays;

public enum FormaPagamento {
    DINHEIRO("Dinheiro", "DIN"),
    PIX("Pix", "PIX"),
    DEBITO("Débito", "DEB"),
    CREDITO("Crédito", "CCR");
    private final String nome;
    private final String sigla;

    // Construtor
    private FormaPagamento(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public static FormaPagamento fromSigla(String sigla) {
        return Arrays.stream(FormaPagamento.values())
                .filter(fp -> fp.sigla.equalsIgnoreCase(sigla))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Forma de pagamento inválida: " + sigla));
    }
}

