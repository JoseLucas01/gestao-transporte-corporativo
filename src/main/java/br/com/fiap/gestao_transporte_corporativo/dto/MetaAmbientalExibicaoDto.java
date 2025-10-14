package br.com.fiap.gestao_transporte_corporativo.dto;

import br.com.fiap.gestao_transporte_corporativo.model.MetaAmbiental;
import br.com.fiap.gestao_transporte_corporativo.model.Transporte;

public record MetaAmbientalExibicaoDto(
        Long id,
        Long transporte,
        String descricao,
        float maxKmPorMes
) {

    public MetaAmbientalExibicaoDto(MetaAmbiental metaAmbiental) {
        this(metaAmbiental.getId(), metaAmbiental.getTransporte().getId(), metaAmbiental.getDescricao(), metaAmbiental.getMaxKmPorMes());
    }

}
