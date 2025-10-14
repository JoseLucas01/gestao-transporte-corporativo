package br.com.fiap.gestao_transporte_corporativo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FuncionarioCadastroDto(
        Long id,

        @NotBlank(message = "O nome do funcionário é obrigatório!")
        String nome,

        @NotNull(message = "O uso de transporte alternativo do funcionário é obrigatório!")
        Boolean transporteAlternativo,

        @NotNull(message = "O uso de veiculo corporativo do funcionário é obrigatório!")
        Boolean veiculoCorporativo
) {
}
