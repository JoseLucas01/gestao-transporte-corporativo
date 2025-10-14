package br.com.fiap.gestao_transporte_corporativo.service;

import br.com.fiap.gestao_transporte_corporativo.dto.TransporteCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.dto.TransporteExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.exception.TransporteNaoEncontradoException;
import br.com.fiap.gestao_transporte_corporativo.model.Transporte;
import br.com.fiap.gestao_transporte_corporativo.repository.TransporteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransporteService {

    @Autowired
    private TransporteRepository transporteRepository;

    // Listar todos os transportes
    public Page<TransporteExibicaoDto> listarTodos(Pageable paginacao) {
        return transporteRepository.findAll(paginacao).map(TransporteExibicaoDto::new);
    }

    // Obter um transporte por ID
    public TransporteExibicaoDto obterPorId(Long id) {
        Optional<Transporte> transporte = transporteRepository.findById(id);

        if(transporte.isPresent()) {
            return new TransporteExibicaoDto(transporte.get());
        }
        else {
            throw new TransporteNaoEncontradoException("Transporte não encontrado!");
        }
    }

    // Listar transportes por modelo
    public Page<TransporteExibicaoDto> listarPorModelo(String modelo, Pageable paginacao) {
        return transporteRepository.findByModelo(modelo, paginacao).map(TransporteExibicaoDto::new);
    }

    // Listar transportes por tipo de combustível
    public Page<TransporteExibicaoDto> listarPorTipoCombustivel(String tipoCombustivel, Pageable paginacao) {
        return transporteRepository.findByTipoCombustivel(tipoCombustivel, paginacao).map(TransporteExibicaoDto::new);
    }

    // Listar transportes entre km/l mínimo e máximo
    public Page<TransporteExibicaoDto> listarEntreKmPorLitro(float kmPorLitroMin, float kmPorLitroMax, Pageable paginacao) {
        return transporteRepository.findByKmPorLitroBetween(kmPorLitroMin, kmPorLitroMax, paginacao).map(TransporteExibicaoDto::new);
    }

    // Criar um novo transporte
    public TransporteExibicaoDto criar(TransporteCadastroDto transporteCadastroDto) {
        Transporte transporte = new Transporte();
        BeanUtils.copyProperties(transporteCadastroDto, transporte);

        return new TransporteExibicaoDto(transporteRepository.save(transporte));
    }

    // Atualizar um transporte existente
    public TransporteExibicaoDto atualizar(Transporte transporte) {
        Optional<Transporte> transporteOptional = transporteRepository.findById(transporte.getId());

        if(transporteOptional.isPresent()) {
            return new TransporteExibicaoDto(transporteRepository.save(transporte));
        }
        else {
            throw new TransporteNaoEncontradoException("Transporte não encontrado!");
        }
    }

    // Excluir um transporte por ID
    public void excluir(Long id) {
        Optional<Transporte> transporteOptional = transporteRepository.findById(id);

        if (transporteOptional.isPresent()) {
            transporteRepository.delete(transporteOptional.get());
        }
        else {
            throw new TransporteNaoEncontradoException("Transporte não encontrado!");
        }
    }
}