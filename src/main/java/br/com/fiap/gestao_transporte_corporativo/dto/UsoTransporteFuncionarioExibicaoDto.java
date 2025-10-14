package br.com.fiap.gestao_transporte_corporativo.dto;

import br.com.fiap.gestao_transporte_corporativo.model.Funcionario;
import br.com.fiap.gestao_transporte_corporativo.model.Transporte;
import br.com.fiap.gestao_transporte_corporativo.model.UsoTransporteFuncionario;

import java.time.LocalDate;

public record UsoTransporteFuncionarioExibicaoDto(
        Long id,
        Long transporte,
        Long funcionario,
        LocalDate dataUso,
        float kmRodados
) {

    public UsoTransporteFuncionarioExibicaoDto(UsoTransporteFuncionario usoTransporteFuncionario) {
        this(usoTransporteFuncionario.getId(), usoTransporteFuncionario.getTransporte().getId(), usoTransporteFuncionario.getFuncionario().getId(), usoTransporteFuncionario.getDataUso(), usoTransporteFuncionario.getKmRodado());
    }
}
