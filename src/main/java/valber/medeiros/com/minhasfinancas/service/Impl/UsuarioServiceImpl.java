package valber.medeiros.com.minhasfinancas.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import valber.medeiros.com.minhasfinancas.exepction.ErroAutenticacaoException;
import valber.medeiros.com.minhasfinancas.exepction.RegraNegocioException;
import valber.medeiros.com.minhasfinancas.model.entity.Usuario;
import valber.medeiros.com.minhasfinancas.model.repository.UsuarioRepository;
import valber.medeiros.com.minhasfinancas.service.UsuarioService;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        Optional<Usuario> usuarioOptional = repository.findByEmail(email);

        if (!usuarioOptional.isPresent()) {
            throw new ErroAutenticacaoException("Usuário não encontrado para o email informado.");
        }

        var usuario = usuarioOptional.get();
        if (!usuario.getSenha().equals(senha)) {
            throw new ErroAutenticacaoException("Senha inválida.");
        }

        return usuario;
    }

    @Override
    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        this.validarEmail(usuario.getEmail());
        return repository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = repository.existsByEmail(email);
        if (existe) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
        }
    }
}
