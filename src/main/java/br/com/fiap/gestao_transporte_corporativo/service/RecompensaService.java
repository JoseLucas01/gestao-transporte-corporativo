package br.com.fiap.gestao_transporte_corporativo.service;

import br.com.fiap.gestao_transporte_corporativo.dto.RecompensaCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.dto.RecompensaExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.exception.RecompensaNaoEncontradaException;
import br.com.fiap.gestao_transporte_corporativo.model.Funcionario;
import br.com.fiap.gestao_transporte_corporativo.model.Recompensa;
import br.com.fiap.gestao_transporte_corporativo.repository.FuncionarioRepository;
import br.com.fiap.gestao_transporte_corporativo.repository.RecompensaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecompensaService {

    @Autowired
    private RecompensaRepository repository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Listar todos as recompensas
    public Page<RecompensaExibicaoDto> listarTodos(Pageable paginacao) {
        return repository.findAll(paginacao).map(RecompensaExibicaoDto::new);
    }

    // Obter uma recompensa por ID
    public RecompensaExibicaoDto obterPorId(Long id) {
        Optional<Recompensa> recompensa = repository.findById(id);

        if(recompensa.isPresent()) {
            return new RecompensaExibicaoDto(recompensa.get());
        }
        else {
            throw new RecompensaNaoEncontradaException("Recompensa não encontrada!");
        }
    }

    // Obter uma recompensa por ID do funcionario
    public Page<RecompensaExibicaoDto> listarPorIdFuncionario(Long idFuncionario, Pageable paginacao) {
        return repository.findByFuncionario_Id(idFuncionario, paginacao).map(RecompensaExibicaoDto::new);
    }

    // Criar uma nova recompensa
    public RecompensaExibicaoDto criar(RecompensaCadastroDto recompensaCadastroDto) {
        Recompensa recompensa = new Recompensa();
        Optional<Funcionario> funcionario = funcionarioRepository.findById(recompensaCadastroDto.funcionario());

        recompensa.setFuncionario(funcionario.get());
        recompensa.setDescricao(recompensaCadastroDto.descricao());
        recompensa.setValor(recompensaCadastroDto.valor());

        return new RecompensaExibicaoDto(repository.save(recompensa));
    }

    // Atualizar uma recompensa existente
    public RecompensaExibicaoDto atualizar(RecompensaCadastroDto recompensaCadastroDto) {
        Recompensa recompensa = new Recompensa();
        Optional<Funcionario> funcionario = funcionarioRepository.findById(recompensaCadastroDto.funcionario());

        recompensa.setId(recompensaCadastroDto.id());
        recompensa.setFuncionario(funcionario.get());
        recompensa.setDescricao(recompensaCadastroDto.descricao());
        recompensa.setValor(recompensaCadastroDto.valor());

        return new RecompensaExibicaoDto(repository.save(recompensa));
    }

    // Excluir uma recompensa por ID
    public void excluir(Long id) {
        Optional<Recompensa> recompensa = repository.findById(id);

        if (recompensa.isPresent()) {
            repository.delete(recompensa.get());
        }
        else {
            throw new RecompensaNaoEncontradaException("Recompensa não encontrada!");
        }
    }

}