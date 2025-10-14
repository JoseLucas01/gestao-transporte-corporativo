package br.com.fiap.gestao_transporte_corporativo.controller;

import br.com.fiap.gestao_transporte_corporativo.dto.UsuarioCadastroDto;
import br.com.fiap.gestao_transporte_corporativo.dto.UsuarioExibicaoDto;
import br.com.fiap.gestao_transporte_corporativo.model.Usuario;
import br.com.fiap.gestao_transporte_corporativo.service.UsuarioService;
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
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto salvar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto) {
        return service.salvarUsuario(usuarioCadastroDto);
    }

    @GetMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDto buscarPorId(@PathVariable Long id) {
        return service.buscarUsuarioPorId(id);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioExibicaoDto> buscarTodosUsuarios(@PageableDefault(size=10, page=0, sort="id", direction=Sort.Direction.DESC) Pageable paginacao) {
        return service.listarTodosUsuarios(paginacao);
    }

    @DeleteMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuarioPorId(@PathVariable Long id) {
        service.excluirUsuarioPorId(id);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDto atualizar(@RequestBody Usuario usuario) {
        return service.atualizarUsuario(usuario);
    }
}
