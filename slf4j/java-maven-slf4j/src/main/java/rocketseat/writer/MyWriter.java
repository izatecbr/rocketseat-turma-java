package rocketseat.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyWriter {
    private static final Logger logger = LoggerFactory.getLogger(MyWriter.class);
    public void toWrite(){
        //aplicando o debug dos valores ao tentar instanciar e atribuir os valores dos objetos
        logger.debug("definindo a data: 2025-04-01 para o objeto");
        logger.debug("definindo o valor: 27.75 para o objeto");
    }
}
