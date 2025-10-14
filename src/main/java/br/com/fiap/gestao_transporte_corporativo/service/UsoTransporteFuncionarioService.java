package br.com.fiap.gestao_transporte_corporativo.service;

import br.com.fiap.gestao_transporte_corporativo.dto.UsoTransporteFuncionarioExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.dto.UsoTransporteFuncionarioCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.exception.UsoTransFuncNaoEncontradoException;
import br.com.fiap.gestao_transporte_corporativo.model.Funcionario;
import br.com.fiap.gestao_transporte_corporativo.model.Transporte;
import br.com.fiap.gestao_transporte_corporativo.model.UsoTransporteFuncionario;
import br.com.fiap.gestao_transporte_corporativo.repository.FuncionarioRepository;
import br.com.fiap.gestao_transporte_corporativo.repository.TransporteRepository;
import br.com.fiap.gestao_transporte_corporativo.repository.UsoTransporteFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UsoTransporteFuncionarioService {
    
    @Autowired
    private UsoTransporteFuncionarioRepository repository;

    @Autowired
    private TransporteRepository transporteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Listar todos os usos
    public Page<UsoTransporteFuncionarioExibicaoDto> listarTodos(Pageable paginacao) {
        return repository.findAll(paginacao).map(UsoTransporteFuncionarioExibicaoDto::new);
    }

    // Obter um uso por ID
    public UsoTransporteFuncionarioExibicaoDto obterPorId(Long id) {
        Optional<UsoTransporteFuncionario> uso = repository.findById(id);

        if(uso.isPresent()) {
            return new UsoTransporteFuncionarioExibicaoDto(uso.get());
        }
        else {
            throw new UsoTransFuncNaoEncontradoException("Uso de Transporte não encontrado!");
        }
    }

    // Obter um uso por ID do transporte
    public Page<UsoTransporteFuncionarioExibicaoDto> listarPorIdTransporte(Long idTransporte, Pageable paginacao) {
        return repository.findByTransporte_Id(idTransporte, paginacao).map(UsoTransporteFuncionarioExibicaoDto::new);
    }

    // Obter um uso por ID do funcionario
    public Page<UsoTransporteFuncionarioExibicaoDto> listarPorIdFuncionario(Long idFuncionario, Pageable paginacao) {
        return repository.findByFuncionario_Id(idFuncionario, paginacao).map(UsoTransporteFuncionarioExibicaoDto::new);
    }

    // Listar usos entre data inicial e final
    public Page<UsoTransporteFuncionarioExibicaoDto> listarEntreDatas(LocalDate dataInicial, LocalDate dataFinal, Pageable paginacao) {
        return repository.findByDataUsoBetween(dataInicial, dataFinal, paginacao).map(UsoTransporteFuncionarioExibicaoDto::new);
    }

    // Criar um novo uso
    public UsoTransporteFuncionarioExibicaoDto criar(UsoTransporteFuncionarioCadastroDto funcionarioCadastroDto) {
        UsoTransporteFuncionario uso = new UsoTransporteFuncionario();
        Optional<Transporte> transporte = transporteRepository.findById(funcionarioCadastroDto.transporte());
        Optional<Funcionario> funcionario = funcionarioRepository.findById(funcionarioCadastroDto.funcionario());

        uso.setTransporte(transporte.get());
        uso.setFuncionario(funcionario.get());
        uso.setDataUso(funcionarioCadastroDto.dataUso());
        uso.setKmRodado(funcionarioCadastroDto.kmRodados());

        return new UsoTransporteFuncionarioExibicaoDto(repository.save(uso));
    }

    // Atualizar um uso existente
    public UsoTransporteFuncionarioExibicaoDto atualizar(UsoTransporteFuncionarioCadastroDto funcionarioCadastroDto) {
        UsoTransporteFuncionario uso = new UsoTransporteFuncionario();
        Optional<Transporte> transporte = transporteRepository.findById(funcionarioCadastroDto.transporte());
        Optional<Funcionario> funcionario = funcionarioRepository.findById(funcionarioCadastroDto.funcionario());

        uso.setId(funcionarioCadastroDto.id());
        uso.setTransporte(transporte.get());
        uso.setFuncionario(funcionario.get());
        uso.setDataUso(funcionarioCadastroDto.dataUso());
        uso.setKmRodado(funcionarioCadastroDto.kmRodados());

        return new UsoTransporteFuncionarioExibicaoDto(repository.save(uso));
    }

    // Excluir um uso por ID
    public void excluir(Long id) {
        Optional<UsoTransporteFuncionario> funcionarioOptional = repository.findById(id);

        if (funcionarioOptional.isPresent()) {
            repository.delete(funcionarioOptional.get());
        }
        else {
            throw new UsoTransFuncNaoEncontradoException("Uso de Transporte não encontrado!");
        }
    }
    
}
