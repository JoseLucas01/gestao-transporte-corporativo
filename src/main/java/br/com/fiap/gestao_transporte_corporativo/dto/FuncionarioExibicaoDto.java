package br.com.fiap.gestao_transporte_corporativo.dto;

import br.com.fiap.gestao_transporte_corporativo.model.Funcionario;

public record FuncionarioExibicaoDto(
        Long id,
        String nome,
        Boolean transporteAlternativo,
        Boolean veiculoCorporativo
) {

    public FuncionarioExibicaoDto(Funcionario funcionario) {
        this(funcionario.getId(), funcionario.getNome(), funcionario.getTransporteAlternativo(), funcionario.getVeiculoCorporativo());
    }

}
