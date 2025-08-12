package com.example.demo;

public class SimulacaoEnum {
    public static void main(String[] args) {
        String linha = "distrito_federal";
        EstadoBrasileiro estadoBrasileiro = EstadoBrasileiro.valueOf(linha.toUpperCase());
        System.out.println(estadoBrasileiro.getSigla() + " " + estadoBrasileiro.getMinusculo());
    }
}
