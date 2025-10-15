package br.com.fiap.gestao_transporte_corporativo.service;

import br.com.fiap.gestao_transporte_corporativo.dto.UsoTransporteFuncionarioCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.dto.UsoTransporteFuncionarioExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.exception.UsoTransFuncNaoEncontradoException;
import br.com.fiap.gestao_transporte_corporativo.model.Funcionario;
import br.com.fiap.gestao_transporte_corporativo.model.Transporte;
import br.com.fiap.gestao_transporte_corporativo.model.UsoTransporteFuncionario;
import br.com.fiap.gestao_transporte_corporativo.repository.FuncionarioRepository;
import br.com.fiap.gestao_transporte_corporativo.repository.TransporteRepository;
import br.com.fiap.gestao_transporte_corporativo.repository.UsoTransporteFuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsoTransporteFuncionarioServiceTest {

    @Mock
    private UsoTransporteFuncionarioRepository usoRepository;

    @Mock
    private TransporteRepository transporteRepository;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private UsoTransporteFuncionarioService usoService;

    @Test
    void deveCriarUsoDeTransporteComSucesso() {
        // Arrange
        Long transporteId = 1L;
        Long funcionarioId = 2L;
        UsoTransporteFuncionarioCadastroDto cadastroDto = new UsoTransporteFuncionarioCadastroDto(
                0L, transporteId, funcionarioId, LocalDate.now(), 55.0f
        );

        Transporte transporteMock = new Transporte();
        transporteMock.setId(transporteId);
        Funcionario funcionarioMock = new Funcionario();
        funcionarioMock.setId(funcionarioId);

        UsoTransporteFuncionario usoSalvo = new UsoTransporteFuncionario();
        usoSalvo.setId(30L);
        usoSalvo.setTransporte(transporteMock);
        usoSalvo.setFuncionario(funcionarioMock);
        usoSalvo.setKmRodado(cadastroDto.kmRodados());

        when(transporteRepository.findById(transporteId)).thenReturn(Optional.of(transporteMock));
        when(funcionarioRepository.findById(funcionarioId)).thenReturn(Optional.of(funcionarioMock));
        when(usoRepository.save(any(UsoTransporteFuncionario.class))).thenReturn(usoSalvo);

        // Act
        UsoTransporteFuncionarioExibicaoDto resultado = usoService.criar(cadastroDto);

        // Assert
        assertNotNull(resultado);
        assertEquals(cadastroDto.kmRodados(), resultado.kmRodados());
        verify(transporteRepository, times(1)).findById(transporteId);
        verify(funcionarioRepository, times(1)).findById(funcionarioId);
        verify(usoRepository, times(1)).save(any(UsoTransporteFuncionario.class));
    }

    @Test
    void deveLancarExcecaoAoObterUsoInexistente() {
        // Arrange
        Long idInexistente = 99L;
        when(usoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                UsoTransFuncNaoEncontradoException.class,
                () -> usoService.obterPorId(idInexistente)
        );
    }
}