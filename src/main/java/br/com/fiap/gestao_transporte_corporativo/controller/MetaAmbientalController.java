package br.com.fiap.gestao_transporte_corporativo.controller;

import br.com.fiap.gestao_transporte_corporativo.dto.MetaAmbientalCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.dto.MetaAmbientalExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.model.MetaAmbiental;
import br.com.fiap.gestao_transporte_corporativo.service.MetaAmbientalService;
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
public class MetaAmbientalController {

    @Autowired
    private MetaAmbientalService service;

    @GetMapping("/meta-ambiental")
    @ResponseStatus(HttpStatus.OK)
    public Page<MetaAmbientalExibicaoDto> listarTransportes(@PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable paginacao) {
        return service.listarTodos(paginacao);
    }

    @GetMapping("/meta-ambiental/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MetaAmbientalExibicaoDto buscarPorId(@PathVariable Long id) {
        return service.obterPorId(id);
    }

    @GetMapping("/meta-ambiental/transporte/{idTransporte}")
    @ResponseStatus(HttpStatus.OK)
    public Page<MetaAmbientalExibicaoDto> listarPorIdTransporte(@PathVariable Long idTransporte, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable paginacao) {
        return service.listarPorIdTransporte(idTransporte, paginacao);
    }
    
    @GetMapping("/meta-ambiental/km-max/{kmMin}/{kmMax}")
    @ResponseStatus(HttpStatus.OK)
    public Page<MetaAmbientalExibicaoDto> listarEntreDatas(@PathVariable float kmMin, @PathVariable float kmMax, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable paginacao) {
        return service.listarEntreKmMinMax(kmMin, kmMax, paginacao);
    }

    @PostMapping("/meta-ambiental")
    @ResponseStatus(HttpStatus.CREATED)
    public MetaAmbientalExibicaoDto cadastrarMetaAmbiental(@RequestBody @Valid MetaAmbientalCadastroDto metaAmbientalCadastroDto) {
        return service.criar(metaAmbientalCadastroDto);
    }

    @PutMapping("/meta-ambiental")
    @ResponseStatus(HttpStatus.OK)
    public MetaAmbientalExibicaoDto atualizarMetaAmbiental(@RequestBody @Valid MetaAmbientalCadastroDto metaAmbiental) {
        return service.atualizar(metaAmbiental);
    }

    @DeleteMapping("/meta-ambiental/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarMetaAmbiental(@PathVariable Long id) {
        service.excluir(id);
    }
}
