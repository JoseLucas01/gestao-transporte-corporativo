package br.com.fiap.gestao_transporte_corporativo.controller;

import br.com.fiap.gestao_transporte_corporativo.dto.RecompensaCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.dto.RecompensaExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.service.RecompensaService;
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
public class RecompensaController {

    @Autowired
    private RecompensaService service;

    @GetMapping("/recompensas")
    @ResponseStatus(HttpStatus.OK)
    public Page<RecompensaExibicaoDto> listarTransportes(@PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable paginacao) {
        return service.listarTodos(paginacao);
    }

    @GetMapping("/recompensas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecompensaExibicaoDto buscarPorId(@PathVariable Long id) {
        return service.obterPorId(id);
    }

    @GetMapping("/recompensas/funcionario/{idFuncionario}")
    @ResponseStatus(HttpStatus.OK)
    public Page<RecompensaExibicaoDto> listarPorIdFuncionario(@PathVariable Long idFuncionario, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable paginacao) {
        return service.listarPorIdFuncionario(idFuncionario, paginacao);
    }

    @PostMapping("/recompensas")
    @ResponseStatus(HttpStatus.CREATED)
    public RecompensaExibicaoDto cadastrarRecompensa(@RequestBody @Valid RecompensaCadastroDto recompensaCadastroDto) {
        return service.criar(recompensaCadastroDto);
    }

    @PutMapping("/recompensas")
    @ResponseStatus(HttpStatus.OK)
    public RecompensaExibicaoDto atualizarRecompensa(@RequestBody @Valid RecompensaCadastroDto recompensa) {
        return service.atualizar(recompensa);
    }

    @DeleteMapping("/recompensas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarRecompensa(@PathVariable Long id) {
        service.excluir(id);
    }
}
