package br.com.fiap.gestao_transporte_corporativo.dto;

import br.com.fiap.gestao_transporte_corporativo.model.Funcionario;
import br.com.fiap.gestao_transporte_corporativo.model.Transporte;
import br.com.fiap.gestao_transporte_corporativo.model.UsoTransporteFuncionario;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UsoTransporteFuncionarioCadastroDto(
        Long id,

        @NotNull(message = "O transporte utilizado é obrigatório!")
        Long transporte,

        @NotNull(message = "O funcionário que utilizou é obrigatório!")
        Long funcionario,

        @NotNull(message = "O data de uso é obrigatória!")
        LocalDate dataUso,

        @NotNull(message = "A quantidade de Km rodados é obrigatória!")
        @DecimalMin(value = "1.0", message = "A quantidade de Km rodados deve ser maior que 1.0")
        @DecimalMax(value = "1000.0", message = "A quantidade de Km rodados deve ser menor que 1000.0")
        float kmRodados
) {
}
