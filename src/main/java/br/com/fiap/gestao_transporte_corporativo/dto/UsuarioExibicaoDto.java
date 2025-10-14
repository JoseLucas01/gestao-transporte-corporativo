package br.com.fiap.gestao_transporte_corporativo.dto;

import br.com.fiap.gestao_transporte_corporativo.model.Usuario;
import br.com.fiap.gestao_transporte_corporativo.model.UsuarioRole;

public record UsuarioExibicaoDto(
        long id,
        String nome,
        String email,
        UsuarioRole role
) {

    public UsuarioExibicaoDto(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getRole());
    }

}
