package rocketseat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculadora {
    private static final Logger logger = LoggerFactory.getLogger(Calculadora.class);

    public void somar(int x, int y){
        int z = x+y;
        logger.debug("a soma de {} + {} é igual a {} ", x, y,z);
    }
    public void subtrair(int x, int y){
        int z = x-y;
        logger.trace("a subtracao de {} + {} é igual a {} ", x, y,z);
    }
}
