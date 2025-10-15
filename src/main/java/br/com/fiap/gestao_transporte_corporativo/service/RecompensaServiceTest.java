package br.com.fiap.gestao_transporte_corporativo.service;

import br.com.fiap.gestao_transporte_corporativo.dto.RecompensaCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.dto.RecompensaExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.exception.RecompensaNaoEncontradaException;
import br.com.fiap.gestao_transporte_corporativo.model.Funcionario;
import br.com.fiap.gestao_transporte_corporativo.model.Recompensa;
import br.com.fiap.gestao_transporte_corporativo.repository.FuncionarioRepository;
import br.com.fiap.gestao_transporte_corporativo.repository.RecompensaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecompensaServiceTest {

    @Mock
    private RecompensaRepository recompensaRepository;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private RecompensaService recompensaService;

    @Test
    void deveCriarRecompensaComSucesso() {
        Long funcionarioId = 1L;
        RecompensaCadastroDto cadastroDto = new RecompensaCadastroDto(0L, funcionarioId, "Vale-presente", 100.0f);
        Funcionario funcionarioMock = new Funcionario();
        funcionarioMock.setId(funcionarioId);

        Recompensa recompensaSalva = new Recompensa();
        recompensaSalva.setId(20L);
        recompensaSalva.setDescricao(cadastroDto.descricao());
        recompensaSalva.setFuncionario(funcionarioMock);

        when(funcionarioRepository.findById(funcionarioId)).thenReturn(Optional.of(funcionarioMock));
        when(recompensaRepository.save(any(Recompensa.class))).thenReturn(recompensaSalva);

        // Act
        RecompensaExibicaoDto resultado = recompensaService.criar(cadastroDto);

        // Assert
        assertNotNull(resultado);
        assertEquals(recompensaSalva.getDescricao(), resultado.descricao());
        verify(funcionarioRepository, times(1)).findById(funcionarioId);
        verify(recompensaRepository, times(1)).save(any(Recompensa.class));
    }

    @Test
    void deveLancarExcecaoAoExcluirRecompensaInexistente() {
        Long idInexistente = 99L;
        when(recompensaRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(
                RecompensaNaoEncontradaException.class,
                () -> recompensaService.excluir(idInexistente)
        );

        verify(recompensaRepository, never()).delete(any(Recompensa.class));
    }
}