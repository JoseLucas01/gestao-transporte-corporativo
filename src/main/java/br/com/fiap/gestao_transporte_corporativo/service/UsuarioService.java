package br.com.fiap.gestao_transporte_corporativo.service;

import br.com.fiap.gestao_transporte_corporativo.dto.UsuarioCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.dto.UsuarioExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.exception.UsuarioNaoEncontradoException;
import br.com.fiap.gestao_transporte_corporativo.model.Usuario;
import br.com.fiap.gestao_transporte_corporativo.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDto salvarUsuario(UsuarioCadastroDto usuarioCadastroDto) {

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDto.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);
        usuario.setSenha(senhaCriptografada);

        return new UsuarioExibicaoDto(usuarioRepository.save(usuario));
    }

    public UsuarioExibicaoDto buscarUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDto(usuarioOptional.get());
        }
        else {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado!");
        }
    }

    public Page<UsuarioExibicaoDto> listarTodosUsuarios(Pageable paginacao) {
        return usuarioRepository.findAll(paginacao).map(UsuarioExibicaoDto::new);
    }

    public void excluirUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            usuarioRepository.delete(usuarioOptional.get());
        }
        else {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado!");
        }
    }

    public UsuarioExibicaoDto atualizarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuario.getId());

        if (usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDto(usuarioRepository.save(usuario));
        }
        else {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado!");
        }
    }
}
