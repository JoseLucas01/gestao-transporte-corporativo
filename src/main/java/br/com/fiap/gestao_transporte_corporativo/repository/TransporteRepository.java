package br.com.fiap.gestao_transporte_corporativo.repository;

import br.com.fiap.gestao_transporte_corporativo.model.Transporte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransporteRepository extends JpaRepository<Transporte, Long> {

    Page<Transporte> findByModelo(String modelo, Pageable paginacao);

    Page<Transporte> findByTipoCombustivel(String tipoCombustivel, Pageable paginacao);

    Page<Transporte> findByKmPorLitroBetween(float kmPorLitroMin, float kmPorLitroMax, Pageable paginacao);

}