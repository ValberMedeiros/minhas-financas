package valber.medeiros.com.minhasfinancas.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import valber.medeiros.com.minhasfinancas.model.entity.Usuario;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository repository;

    @Test
    void deveVerificarAExistenciaDeUmEmail() {
        //Cenário
        Usuario usuario = Usuario.builder()
                .nome("usuario")
                .email("usuario@email.com")
                .build();

        repository.save(usuario);
        //ação
        boolean existe = repository.existsByEmail("usuario@email.com");

        //verificação
        Assertions.assertThat(existe).isTrue();
    }

    @Test
    void deveRetornarFalsoQuandoNãoHouverUsuarioCadastradoComOEmail() {
        //Cenário
        repository.deleteAll();

        //Ação
        boolean existe = repository.existsByEmail("usuario@email.com");

        //Verificação
        Assertions.assertThat(existe).isFalse();

    }

}
