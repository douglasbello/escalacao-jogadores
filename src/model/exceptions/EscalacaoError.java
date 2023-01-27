package model.exceptions;

import java.io.Serial;

public class EscalacaoError extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;


    public EscalacaoError(String msg) {
        super(msg);
    }
}
