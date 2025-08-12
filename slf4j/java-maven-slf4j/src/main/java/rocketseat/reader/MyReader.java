package rocketseat.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyReader {
    private static final Logger logger = LoggerFactory.getLogger(MyReader.class);
    public void toRead(){
        //aplicando o trace de das colunas por exemplo
        logger.trace("Lendo a linha 1 coluna 1 -> 2025-04-01");
        logger.trace("Lendo a linha 1 coluna 2 -> 25.75");
    }
}
