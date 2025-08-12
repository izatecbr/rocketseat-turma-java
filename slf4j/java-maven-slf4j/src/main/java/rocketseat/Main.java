package rocketseat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        logger.trace("TRACE-Trace da aplicação...");
        logger.debug("DEBUG-Depurando a aplicação...");
        logger.info("INFO-Iniciando a aplicação...");
        logger.warn("WARN-Isso é um aviso.");
        logger.error("ERROR-Isso é um erro!");
    }
}