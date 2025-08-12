package com.example.demo;

public enum EstadoBrasileiro {
    PIAUI("PI"), MARANHAO("MA"), SAO_PAULO("SP"), DISTRITO_FEDERAL("DF");
    private String sigla;
    EstadoBrasileiro(String sigla){
        this.sigla = sigla;
    }
    public String getSigla() {
        return sigla;
    }
    String getMinusculo(){
        return this.name().toLowerCase();
    }
}
