package br.com.fiap.gestao_transporte_corporativo.service;

import br.com.fiap.gestao_transporte_corporativo.dto.MetaAmbientalCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.dto.MetaAmbientalExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.exception.MetaAmbientalNaoEncontradaException;
import br.com.fiap.gestao_transporte_corporativo.model.MetaAmbiental;
import br.com.fiap.gestao_transporte_corporativo.model.Transporte;
import br.com.fiap.gestao_transporte_corporativo.repository.MetaAmbientalRepository;
import br.com.fiap.gestao_transporte_corporativo.repository.TransporteRepository;
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
class MetaAmbientalServiceTest {

    @Mock
    private MetaAmbientalRepository metaAmbientalRepository;

    @Mock
    private TransporteRepository transporteRepository;

    @InjectMocks
    private MetaAmbientalService metaAmbientalService;

    @Test
    void deveCriarMetaAmbientalComSucesso() {
        // Arrange
        Long transporteId = 1L;
        MetaAmbientalCadastroDto cadastroDto = new MetaAmbientalCadastroDto(0L, transporteId,"Reduzir emissões", 1000f);
        Transporte transporteMock = new Transporte();
        transporteMock.setId(transporteId);

        MetaAmbiental metaSalva = new MetaAmbiental();
        metaSalva.setId(10L);
        metaSalva.setDescricao(cadastroDto.descricao());
        metaSalva.setMaxKmPorMes(cadastroDto.maxKmPorMes());
        metaSalva.setTransporte(transporteMock);

        when(transporteRepository.findById(transporteId)).thenReturn(Optional.of(transporteMock));
        when(metaAmbientalRepository.save(any(MetaAmbiental.class))).thenReturn(metaSalva);

        // Act
        MetaAmbientalExibicaoDto resultado = metaAmbientalService.criar(cadastroDto);

        // Assert
        assertNotNull(resultado);
        assertEquals(metaSalva.getDescricao(), resultado.descricao());
        verify(transporteRepository, times(1)).findById(transporteId);
        verify(metaAmbientalRepository, times(1)).save(any(MetaAmbiental.class));
    }

    @Test
    void deveLancarExcecaoAoObterMetaInexistente() {
        // Arrange
        Long idInexistente = 99L;
        when(metaAmbientalRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                MetaAmbientalNaoEncontradaException.class,
                () -> metaAmbientalService.obterPorId(idInexistente)
        );
    }
}