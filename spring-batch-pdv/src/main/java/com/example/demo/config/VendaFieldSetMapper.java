package com.example.demo.config;

import com.example.demo.model.FormaPagamento;
import com.example.demo.model.Venda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class VendaFieldSetMapper implements FieldSetMapper<Venda> {
    private static final Logger logger = LoggerFactory.getLogger(VendaFieldSetMapper.class);
    @Override
    public Venda mapFieldSet(FieldSet fieldSet) {
        try {
            Venda venda = new Venda();
            venda.setNumeroTransacao(fieldSet.readInt("numeroTransacao"));
            venda.setIdTerminal(fieldSet.readString("idTerminal"));
            venda.setDataMovimento(LocalDate.parse(fieldSet.readString("dataMovimento"), DateTimeFormatter.ofPattern("dd/MM/yy")));
            venda.setValorVenda(fieldSet.readDouble("valorVenda"));
            venda.setFormaPagamento(FormaPagamento.fromSigla(fieldSet.readString("formaPagamento")));
            return venda;
        } catch (Exception e) {
            // Aqui você consegue logar a linha que falhou
            //System.err.println("Erro ao mapear linha: " + Arrays.toString(fieldSet.getValues()) + " → " + e.getMessage());
            logger.error("Erro ao mapear linha: {} → numero transacao {} -> {}", Arrays.toString(fieldSet.getValues()),fieldSet.readInt("numeroTransacao"), e.getMessage());
            throw new IllegalArgumentException(Arrays.toString(fieldSet.getValues()) + " → " + e.getMessage());
        }
    }
}
