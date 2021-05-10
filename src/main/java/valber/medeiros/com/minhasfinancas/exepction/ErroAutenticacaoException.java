package valber.medeiros.com.minhasfinancas.exepction;

public class ErroAutenticacaoException extends RuntimeException{

    public ErroAutenticacaoException(String menssagem) {
        super(menssagem);
    }
}
