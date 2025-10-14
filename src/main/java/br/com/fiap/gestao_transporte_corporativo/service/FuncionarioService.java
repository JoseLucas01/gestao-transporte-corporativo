package br.com.fiap.gestao_transporte_corporativo.service;

import br.com.fiap.gestao_transporte_corporativo.dto.FuncionarioExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.dto.FuncionarioCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.exception.FuncionarioNaoEncontradoException;
import br.com.fiap.gestao_transporte_corporativo.model.Funcionario;
import br.com.fiap.gestao_transporte_corporativo.repository.FuncionarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Listar todos os funcionarios
    public Page<FuncionarioExibicaoDto> listarTodos(Pageable paginacao) {
        return funcionarioRepository.findAll(paginacao).map(FuncionarioExibicaoDto::new);
    }

    // Obter um funcionario por ID
    public FuncionarioExibicaoDto obterPorId(Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);

        if(funcionario.isPresent()) {
            return new FuncionarioExibicaoDto(funcionario.get());
        }
        else {
            throw new FuncionarioNaoEncontradoException("Funcionário não encontrado!");
        }
    }

    // Obter um funcionario por nome
    public FuncionarioExibicaoDto obterPorNome(String nome) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByNome(nome);

        if(funcionario.isPresent()) {
            return new FuncionarioExibicaoDto(funcionario.get());
        }
        else {
            throw new FuncionarioNaoEncontradoException("Funcionário não encontrado!");
        }
    }

    // Listar funcionarios por transporte alternativo
    public Page<FuncionarioExibicaoDto> listarPorTransporteAlt(Boolean transporteAlternativo, Pageable paginacao) {
        return funcionarioRepository.findByTransporteAlternativo(transporteAlternativo, paginacao).map(FuncionarioExibicaoDto::new);
    }

    // Listar funcionarios por veiculo corporativo
    public Page<FuncionarioExibicaoDto> listarPorVeiculoCor(Boolean veiculoCorporativo, Pageable paginacao) {
        return funcionarioRepository.findByVeiculoCorporativo(veiculoCorporativo, paginacao).map(FuncionarioExibicaoDto::new);
    }

    // Criar um novo funcionario
    public FuncionarioExibicaoDto criar(FuncionarioCadastroDto funcionarioCadastroDto) {
        Funcionario funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioCadastroDto, funcionario);

        return new FuncionarioExibicaoDto(funcionarioRepository.save(funcionario));
    }

    // Atualizar um funcionario existente
    public FuncionarioExibicaoDto atualizar(Funcionario funcionario) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(funcionario.getId());

        if(funcionarioOptional.isPresent()) {
            return new FuncionarioExibicaoDto(funcionarioRepository.save(funcionario));
        }
        else {
            throw new FuncionarioNaoEncontradoException("Funcionário não encontrado!");
        }
    }

    // Excluir um funcionario por ID
    public void excluir(Long id) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);

        if (funcionarioOptional.isPresent()) {
            funcionarioRepository.delete(funcionarioOptional.get());
        }
        else {
            throw new FuncionarioNaoEncontradoException("Funcionário não encontrado!");
        }
    }

}
