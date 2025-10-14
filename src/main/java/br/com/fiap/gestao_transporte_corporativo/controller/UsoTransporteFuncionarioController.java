package br.com.fiap.gestao_transporte_corporativo.controller;

import br.com.fiap.gestao_transporte_corporativo.dto.UsoTransporteFuncionarioCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.dto.UsoTransporteFuncionarioExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.model.UsoTransporteFuncionario;
import br.com.fiap.gestao_transporte_corporativo.service.UsoTransporteFuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/gestao-transportes")
public class UsoTransporteFuncionarioController {

    @Autowired
    private UsoTransporteFuncionarioService service;

    @GetMapping("/uso-transporte")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsoTransporteFuncionarioExibicaoDto> listarTransportes(@PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable paginacao) {
        return service.listarTodos(paginacao);
    }

    @GetMapping("/uso-transporte/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsoTransporteFuncionarioExibicaoDto buscarPorId(@PathVariable Long id) {
        return service.obterPorId(id);
    }

    @GetMapping("/uso-transporte/transporte/{idTransporte}")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsoTransporteFuncionarioExibicaoDto> listarPorIdTransporte(@PathVariable Long idTransporte, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable paginacao) {
        return service.listarPorIdTransporte(idTransporte, paginacao);
    }

    @GetMapping("/uso-transporte/funcionario/{idFuncionario}")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsoTransporteFuncionarioExibicaoDto> listarPorIdFuncionario(@PathVariable Long idFuncionario, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable paginacao) {
        return service.listarPorIdFuncionario(idFuncionario, paginacao);
    }

    @GetMapping("/uso-transporte/data-uso/{dataInicial}/{dataFinal}")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsoTransporteFuncionarioExibicaoDto> listarEntreDatas(@PathVariable LocalDate dataInicial, @PathVariable LocalDate dataFinal, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable paginacao) {
        return service.listarEntreDatas(dataInicial, dataFinal, paginacao);
    }

    @PostMapping("/uso-transporte")
    @ResponseStatus(HttpStatus.CREATED)
    public UsoTransporteFuncionarioExibicaoDto cadastrarUsoTransporteFuncionario(@RequestBody @Valid UsoTransporteFuncionarioCadastroDto usoTransporteFuncionario) {
        return service.criar(usoTransporteFuncionario);
    }

    @PutMapping("/uso-transporte")
    @ResponseStatus(HttpStatus.OK)
    public UsoTransporteFuncionarioExibicaoDto atualizarUsoTransporteFuncionario(@RequestBody @Valid UsoTransporteFuncionarioCadastroDto usoTransporteFuncionario) {
        return service.atualizar(usoTransporteFuncionario);
    }

    @DeleteMapping("/uso-transporte/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsoTransporteFuncionario(@PathVariable Long id) {
        service.excluir(id);
    }
}
