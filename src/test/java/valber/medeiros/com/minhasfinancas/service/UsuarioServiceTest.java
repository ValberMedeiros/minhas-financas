package valber.medeiros.com.minhasfinancas.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import valber.medeiros.com.minhasfinancas.exepction.RegraNegocioException;
import valber.medeiros.com.minhasfinancas.model.entity.Usuario;
import valber.medeiros.com.minhasfinancas.model.repository.UsuarioRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class UsuarioServiceTest {

    public static String EMAIL = "fulano@email.com";
    public static String NOME = "Fulano das Flores";

    @Autowired
    UsuarioService service;

    @Autowired
    UsuarioRepository repository;

    @Test
    void deveValidarEmail() {
        // cenario
        repository.deleteAll();
        Assertions.assertDoesNotThrow(() -> {
            // acao
            service.validarEmail(EMAIL);
        });
    }

    @Test
    void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
        Usuario usuario = Usuario.builder().nome(NOME).email(EMAIL).build();
        repository.save(usuario);
        Assertions.assertThrows(RegraNegocioException.class, () -> {
            service.validarEmail(EMAIL);
        });
    }
}
