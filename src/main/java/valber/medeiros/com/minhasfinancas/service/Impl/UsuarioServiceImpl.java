package valber.medeiros.com.minhasfinancas.service.Impl;

import org.springframework.stereotype.Service;
import valber.medeiros.com.minhasfinancas.exepction.RegraNegocioExecption;
import valber.medeiros.com.minhasfinancas.model.entity.Usuario;
import valber.medeiros.com.minhasfinancas.model.repository.UsuarioRepository;
import valber.medeiros.com.minhasfinancas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        return null;
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = repository.existsByEmail(email);
        if (existe) {
            throw new RegraNegocioExecption("Já existe um usuário cadastrado com este email.");
        }
    }
}
