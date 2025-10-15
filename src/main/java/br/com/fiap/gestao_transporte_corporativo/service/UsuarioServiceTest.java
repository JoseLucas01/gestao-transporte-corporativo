package br.com.fiap.gestao_transporte_corporativo.service;

import br.com.fiap.gestao_transporte_corporativo.dto.UsuarioCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.dto.UsuarioExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.exception.UsuarioNaoEncontradoException;
import br.com.fiap.gestao_transporte_corporativo.model.Usuario;
import br.com.fiap.gestao_transporte_corporativo.model.UsuarioRole;
import br.com.fiap.gestao_transporte_corporativo.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock // Cria um "mock" do repositório.
    private UsuarioRepository usuarioRepository;

    @InjectMocks // Cria uma instância do UsuarioService injetando os mocks
    private UsuarioService usuarioService;

    private UsuarioCadastroDto usuarioCadastroDto;
    private Usuario usuario;

    @BeforeEach // Método que roda antes de CADA teste
    void setup() {
        usuarioCadastroDto = new UsuarioCadastroDto(0L, "João Silva", "joao@email.com", "senha123", UsuarioRole.USER);
        usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);
        usuario.setId(1L);
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioExibicaoDto resultado = usuarioService.salvarUsuario(usuarioCadastroDto);

        assertNotNull(resultado);
        assertEquals(usuario.getId(), resultado.id());
        assertEquals(usuario.getNome(), resultado.nome());
        assertEquals(usuario.getEmail(), resultado.email());

        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }


    @Test
    void deveLancarExcecaoAoBuscarUsuarioInexistente() {
        Long idInexistente = 99L;

        when(usuarioRepository.findById(idInexistente)).thenReturn(Optional.empty());

        UsuarioNaoEncontradoException exception = assertThrows(
                UsuarioNaoEncontradoException.class,
                () -> usuarioService.buscarUsuarioPorId(idInexistente)
        );

        assertEquals("Usuário não encontrado!", exception.getMessage());

        verify(usuarioRepository, times(1)).findById(idInexistente);
    }
}