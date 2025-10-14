package br.com.fiap.gestao_transporte_corporativo.service;

import br.com.fiap.gestao_transporte_corporativo.dto.MetaAmbientalCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.dto.MetaAmbientalExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.exception.MetaAmbientalNaoEncontradaException;
import br.com.fiap.gestao_transporte_corporativo.model.MetaAmbiental;
import br.com.fiap.gestao_transporte_corporativo.model.Transporte;
import br.com.fiap.gestao_transporte_corporativo.repository.MetaAmbientalRepository;
import br.com.fiap.gestao_transporte_corporativo.repository.TransporteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MetaAmbientalService {

    @Autowired
    private MetaAmbientalRepository repository;

    @Autowired
    private TransporteRepository transporteRepository;

    // Listar todos as metas
    public Page<MetaAmbientalExibicaoDto> listarTodos(Pageable paginacao) {
        return repository.findAll(paginacao).map(MetaAmbientalExibicaoDto::new);
    }

    // Obter uma meta por ID
    public MetaAmbientalExibicaoDto obterPorId(Long id) {
        Optional<MetaAmbiental> uso = repository.findById(id);

        if(uso.isPresent()) {
            return new MetaAmbientalExibicaoDto(uso.get());
        }
        else {
            throw new MetaAmbientalNaoEncontradaException("Meta Ambiental não encontrada!");
        }
    }

    // Obter uma meta por ID do transporte
    public Page<MetaAmbientalExibicaoDto> listarPorIdTransporte(Long idTransporte, Pageable paginacao) {
        return repository.findByTransporte_Id(idTransporte, paginacao).map(MetaAmbientalExibicaoDto::new);
    }

    // Listar metas entre km min e max
    public Page<MetaAmbientalExibicaoDto> listarEntreKmMinMax(float kmMin, float kmMax, Pageable paginacao) {
        return repository.findByMaxKmPorMesBetween(kmMin, kmMax, paginacao).map(MetaAmbientalExibicaoDto::new);
    }

    // Criar uma nova meta
    public MetaAmbientalExibicaoDto criar(MetaAmbientalCadastroDto metaAmbientalCadastroDto) {
        MetaAmbiental meta = new MetaAmbiental();
        Optional<Transporte> transporte = transporteRepository.findById(metaAmbientalCadastroDto.transporte());

        meta.setTransporte(transporte.get());
        meta.setDescricao(metaAmbientalCadastroDto.descricao());
        meta.setMaxKmPorMes(metaAmbientalCadastroDto.maxKmPorMes());

        return new MetaAmbientalExibicaoDto(repository.save(meta));
    }

    // Atualizar uma meta existente
    public MetaAmbientalExibicaoDto atualizar(MetaAmbientalCadastroDto metaAmbientalCadastroDto) {
        MetaAmbiental meta = new MetaAmbiental();
        Optional<Transporte> transporte = transporteRepository.findById(metaAmbientalCadastroDto.transporte());

        meta.setId(metaAmbientalCadastroDto.id());
        meta.setTransporte(transporte.get());
        meta.setDescricao(metaAmbientalCadastroDto.descricao());
        meta.setMaxKmPorMes(metaAmbientalCadastroDto.maxKmPorMes());

        return new MetaAmbientalExibicaoDto(repository.save(meta));
    }

    // Excluir uma meta por ID
    public void excluir(Long id) {
        Optional<MetaAmbiental> metaAmbiental = repository.findById(id);

        if (metaAmbiental.isPresent()) {
            repository.delete(metaAmbiental.get());
        }
        else {
            throw new MetaAmbientalNaoEncontradaException("Meta Ambiental não encontrada!");
        }
    }

}
