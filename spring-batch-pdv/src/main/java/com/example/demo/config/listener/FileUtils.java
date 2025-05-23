package com.example.demo.config.listener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileUtils {

    public static void moverArquivoParaProcessados(String arquivoOrigem, String diretorioDestino) throws IOException {
        Path origemPath = Path.of(arquivoOrigem);
        Path destinoPath = Path.of(diretorioDestino, origemPath.getFileName().toString());

        // Cria o diretório de processados se não existir
        Files.createDirectories(destinoPath.getParent());

        // Move o arquivo
        Files.move(origemPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
    }
}

