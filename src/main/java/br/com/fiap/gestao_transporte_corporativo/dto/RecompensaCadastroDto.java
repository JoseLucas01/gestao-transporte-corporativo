package br.com.fiap.gestao_transporte_corporativo.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecompensaCadastroDto(
        Long id,

        @NotNull(message = "O funcionario recompensado é obrigatório!")
        Long funcionario,

        @NotBlank(message = "A descriçao da recompensa é obrigatória!")
        String descricao,

        @NotNull(message = "O valor da recompensa é obrigatória!")
        @DecimalMin(value = "1.0", message = "O valor da recompensa deve ser maior que 1.0")
        @DecimalMax(value = "50000.0", message = "O valor da recompensa deve ser menor que 50000.0")
        float valor
) {
}
