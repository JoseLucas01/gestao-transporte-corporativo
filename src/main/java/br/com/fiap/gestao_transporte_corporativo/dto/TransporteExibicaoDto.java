package br.com.fiap.gestao_transporte_corporativo.dto;

import br.com.fiap.gestao_transporte_corporativo.model.Transporte;
import jakarta.persistence.Column;

import java.time.LocalDate;

public record TransporteExibicaoDto(
    long id,
    String modelo,
    String tipoCombustivel,
    float kmPorLitro,
    LocalDate ultimaManutencao
) {

    public TransporteExibicaoDto(Transporte transporte) {
        this(transporte.getId(),
                transporte.getModelo(),
                transporte.getTipoCombustivel(),
                transporte.getKmPorLitro(),
                transporte.getUltimaManutencao());
    }

}
