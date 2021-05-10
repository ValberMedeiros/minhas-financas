package valber.medeiros.com.minhasfinancas.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import valber.medeiros.com.minhasfinancas.model.entity.Usuario;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository repository;

    @Test
    public void deveVerificarAExistenciaDeUmEmail() {
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

}
