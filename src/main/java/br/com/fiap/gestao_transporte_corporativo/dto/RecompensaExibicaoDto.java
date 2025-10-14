package br.com.fiap.gestao_transporte_corporativo.dto;

import br.com.fiap.gestao_transporte_corporativo.model.Recompensa;

public record RecompensaExibicaoDto(
        Long id,
        Long funcionario,
        String descricao,
        float valor
) {

    public RecompensaExibicaoDto(Recompensa recompensa) {
        this(recompensa.getId(), recompensa.getFuncionario().getId(), recompensa.getDescricao(), recompensa.getValor());
    }

}
