package br.com.fiap.gestao_transporte_corporativo.controller;

import br.com.fiap.gestao_transporte_corporativo.dto.FuncionarioCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.dto.FuncionarioExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.model.Funcionario;
import br.com.fiap.gestao_transporte_corporativo.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gestao-transportes")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping("/funcionarios")
    @ResponseStatus(HttpStatus.OK)
    public Page<FuncionarioExibicaoDto> listarFuncionarios(@PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable paginacao) {
        return service.listarTodos(paginacao);
    }

    @GetMapping("/funcionarios/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FuncionarioExibicaoDto buscarPorId(@PathVariable Long id) {
        return service.obterPorId(id);
    }

    @GetMapping("/funcionarios/nome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public FuncionarioExibicaoDto buscarPorNome(@PathVariable String nome) {
        return service.obterPorNome(nome);
    }

    @GetMapping("/funcionarios/transporte-alternativo/{transporteAlternativo}")
    @ResponseStatus(HttpStatus.OK)
    public Page<FuncionarioExibicaoDto> listarPorTransporteAlternativo(@PathVariable Boolean transporteAlternativo, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable paginacao) {
        return service.listarPorTransporteAlt(transporteAlternativo, paginacao);
    }

    @GetMapping("/funcionarios/veiculo-corporativo/{veiculoCorporativo}")
    @ResponseStatus(HttpStatus.OK)
    public Page<FuncionarioExibicaoDto> listarPorVeiculoCorporativo(@PathVariable Boolean veiculoCorporativo, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable paginacao) {
        return service.listarPorVeiculoCor(veiculoCorporativo, paginacao);
    }

    @PostMapping("/funcionarios")
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioExibicaoDto cadastrarFuncionario(@RequestBody @Valid FuncionarioCadastroDto funcionario) {
        return service.criar(funcionario);
    }

    @PutMapping("/funcionarios")
    @ResponseStatus(HttpStatus.OK)
    public FuncionarioExibicaoDto atualizarFuncionario(@RequestBody Funcionario funcionario) {
        return service.atualizar(funcionario);
    }

    @DeleteMapping("/funcionarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarFuncionario(@PathVariable Long id) {
        service.excluir(id);
    }
}