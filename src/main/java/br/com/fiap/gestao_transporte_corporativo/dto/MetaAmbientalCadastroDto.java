package br.com.fiap.gestao_transporte_corporativo.dto;

import br.com.fiap.gestao_transporte_corporativo.model.Transporte;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MetaAmbientalCadastroDto(
        Long id,

        @NotNull(message = "O transporte referente à meta é obrigatório!")
        Long transporte,

        @NotBlank(message = "O descrição da meta é obrigatória!")
        String descricao,

        @NotNull(message = "A quantidade de Km máxima por mês é obrigatória!")
        @DecimalMin(value = "1.0", message = "A quantidade de Km máxima por mês deve ser maior que 1.0")
        @DecimalMax(value = "10000.0", message = "A quantidade de Km máxima por mês deve ser menor que 10000.0")
        float maxKmPorMes
) {
}
