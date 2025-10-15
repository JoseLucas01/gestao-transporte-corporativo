package br.com.fiap.gestao_transporte_corporativo.service;

import br.com.fiap.gestao_transporte_corporativo.dto.FuncionarioCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.dto.FuncionarioExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.exception.FuncionarioNaoEncontradoException;
import br.com.fiap.gestao_transporte_corporativo.model.Funcionario;
import br.com.fiap.gestao_transporte_corporativo.repository.FuncionarioRepository;
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
class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Test
    void deveCriarFuncionarioComSucesso() {
        FuncionarioCadastroDto cadastroDto = new FuncionarioCadastroDto(0L, "Ana Souza", true, false);
        Funcionario funcionarioSalvo = new Funcionario();
        BeanUtils.copyProperties(cadastroDto, funcionarioSalvo);
        funcionarioSalvo.setId(1L);

        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionarioSalvo);

        FuncionarioExibicaoDto resultado = funcionarioService.criar(cadastroDto);

        assertNotNull(resultado);
        assertEquals(funcionarioSalvo.getNome(), resultado.nome());
        verify(funcionarioRepository, times(1)).save(any(Funcionario.class));
    }

    @Test
    void deveLancarExcecaoAoObterFuncionarioInexistente() {
        Long idInexistente = 99L;
        when(funcionarioRepository.findById(idInexistente)).thenReturn(Optional.empty());

        FuncionarioNaoEncontradoException exception = assertThrows(
                FuncionarioNaoEncontradoException.class,
                () -> funcionarioService.obterPorId(idInexistente)
        );

        assertEquals("Funcionário não encontrado!", exception.getMessage());
    }
}