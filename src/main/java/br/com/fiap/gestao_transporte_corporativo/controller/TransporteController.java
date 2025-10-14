package br.com.fiap.gestao_transporte_corporativo.controller;

import br.com.fiap.gestao_transporte_corporativo.dto.TransporteCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.dto.TransporteExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.model.Transporte;
import br.com.fiap.gestao_transporte_corporativo.service.TransporteService;
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
public class TransporteController {

    @Autowired
    private TransporteService service;

    @GetMapping("/transportes")
    @ResponseStatus(HttpStatus.OK)
    public Page<TransporteExibicaoDto> listarTransportes(@PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable paginacao) {
        return service.listarTodos(paginacao);
    }

    @GetMapping("/transportes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransporteExibicaoDto buscarPorId(@PathVariable Long id) {
        return service.obterPorId(id);
    }

    @GetMapping("/transportes/modelo/{modelo}")
    @ResponseStatus(HttpStatus.OK)
    public Page<TransporteExibicaoDto> listarPorModelo(@PathVariable String modelo, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable paginacao) {
        return service.listarPorModelo(modelo, paginacao);
    }

    @GetMapping("/transportes/tipo-combustivel/{tipoCombustivel}")
    @ResponseStatus(HttpStatus.OK)
    public Page<TransporteExibicaoDto> listarPorTipoCombustivel(@PathVariable String tipoCombustivel, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable paginacao) {
        return service.listarPorTipoCombustivel(tipoCombustivel, paginacao);
    }

    @GetMapping("/transportes/km-por-litro/{kmPorLitroMin}/{kmPorLitroMax}")
    @ResponseStatus(HttpStatus.OK)
    public Page<TransporteExibicaoDto> listarKmPorLitro(@PathVariable float kmPorLitroMin, @PathVariable float kmPorLitroMax, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable paginacao) {
        return service.listarEntreKmPorLitro(kmPorLitroMin, kmPorLitroMax, paginacao);
    }

    @PostMapping("/transportes")
    @ResponseStatus(HttpStatus.CREATED)
    public TransporteExibicaoDto cadastrarTransporte(@RequestBody @Valid TransporteCadastroDto transporte) {
        return service.criar(transporte);
    }

    @PutMapping("/transportes")
    @ResponseStatus(HttpStatus.OK)
    public TransporteExibicaoDto atualizarTransporte(@RequestBody Transporte transporte) {
        return service.atualizar(transporte);
    }

    @DeleteMapping("/transportes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarTransporte(@PathVariable Long id) {
        service.excluir(id);
    }
}