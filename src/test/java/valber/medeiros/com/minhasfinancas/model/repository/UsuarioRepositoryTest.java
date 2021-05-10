package valber.medeiros.com.minhasfinancas.model.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import valber.medeiros.com.minhasfinancas.model.entity.Usuario;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void deveVerificarAExistenciaDeUmEmail() {
        //Cenário
        Usuario usuario = getUsuario();
        testEntityManager.persist(usuario);
        //ação
        boolean existe = repository.existsByEmail("usuario@email.com");

        //verificação
        Assertions.assertTrue(existe);
    }

    @Test
    void deveRetornarFalsoQuandoNãoHouverUsuarioCadastradoComOEmail() {
        //Ação
        boolean existe = repository.existsByEmail("usuario@email.com");

        //Verificação
        Assertions.assertFalse(existe);
    }

    @Test
    void devePersistirUmUsuarioNaBaseDeDados() {
        //Cenário
        Usuario usuario = getUsuario();

        //Ação
        Usuario save = repository.save(usuario);

        //Verificação
        Assertions.assertNotNull(save.getId());
    }

    @Test
    void deveBuscarUmUsuarioPorEmail() {
        //Cenário
        Usuario usuario = getUsuario();
        testEntityManager.persist(usuario);
        //Verificação
        Optional<Usuario> optionalUsuario = repository.findByEmail("usuario@email.com");
        Assertions.assertTrue(optionalUsuario.isPresent());
    }

    @Test
    void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase() {
        //Cenário
        Usuario usuario = getUsuario();

        //Verificação
        Optional<Usuario> optionalUsuario = repository.findByEmail(usuario.getEmail());
        Assertions.assertTrue(optionalUsuario.isEmpty());
    }

    private Usuario getUsuario() {
        return Usuario.builder()
                .nome("usuario")
                .email("usuario@email.com")
                .senha("senha")
                .build();
    }

}
