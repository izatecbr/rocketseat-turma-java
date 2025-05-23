package com.example.demo.config.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

import java.io.IOException;

public class ArquivoProcessadoListener extends StepExecutionListenerSupport {

    private final String inputFolder;
    private final String processedFolder;

    public ArquivoProcessadoListener(String inputFolder, String processedFolder) {
        this.inputFolder = inputFolder;
        this.processedFolder = processedFolder;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        // Verifica se o Step foi executado com sucesso
        if (stepExecution.getStatus().equals(BatchStatus.COMPLETED)) {
            String arquivo = inputFolder + "/vendas.csv";  // Caminho do arquivo de entrada
            try {
                FileUtils.moverArquivoParaProcessados(arquivo, processedFolder); // Move o arquivo
                System.out.println("Arquivo movido para 'processados'.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.afterStep(stepExecution);
    }
}

