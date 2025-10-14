package br.com.fiap.gestao_transporte_corporativo.repository;

import br.com.fiap.gestao_transporte_corporativo.model.MetaAmbiental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaAmbientalRepository extends JpaRepository<MetaAmbiental, Long> {

    Page<MetaAmbiental> findByTransporte_Id(Long transporteId, Pageable paginacao);

    Page<MetaAmbiental> findByMaxKmPorMesBetween(float kmMin, float kmMax, Pageable paginacao);

}
