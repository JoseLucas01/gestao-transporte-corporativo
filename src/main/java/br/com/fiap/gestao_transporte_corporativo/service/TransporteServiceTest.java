package br.com.fiap.gestao_transporte_corporativo.service;

import br.com.fiap.gestao_transporte_corporativo.dto.TransporteCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.dto.TransporteExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.exception.TransporteNaoEncontradoException;
import br.com.fiap.gestao_transporte_corporativo.model.Transporte;
import br.com.fiap.gestao_transporte_corporativo.repository.TransporteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransporteServiceTest {

    @Mock
    private TransporteRepository transporteRepository;

    @InjectMocks
    private TransporteService transporteService;

    @Test
    void deveCriarTransporteComSucesso() {
        // Arrange
        TransporteCadastroDto cadastroDto = new TransporteCadastroDto(0L, "Onix", "Gasolina", 12.5f, LocalDate.of(2025, Month.JANUARY, 25));
        Transporte transporteSalvo = new Transporte();
        BeanUtils.copyProperties(cadastroDto, transporteSalvo);
        transporteSalvo.setId(1L);

        when(transporteRepository.save(any(Transporte.class))).thenReturn(transporteSalvo);

        // Act
        TransporteExibicaoDto resultado = transporteService.criar(cadastroDto);

        // Assert
        assertNotNull(resultado);
        assertEquals(transporteSalvo.getModelo(), resultado.modelo());
        verify(transporteRepository, times(1)).save(any(Transporte.class));
    }

    @Test
    void deveLancarExcecaoAoExcluirTransporteInexistente() {
        // Arrange
        Long idInexistente = 99L;
        when(transporteRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                TransporteNaoEncontradoException.class,
                () -> transporteService.excluir(idInexistente)
        );

        // Garante que o método delete NUNCA foi chamado
        verify(transporteRepository, never()).delete(any(Transporte.class));
    }
}