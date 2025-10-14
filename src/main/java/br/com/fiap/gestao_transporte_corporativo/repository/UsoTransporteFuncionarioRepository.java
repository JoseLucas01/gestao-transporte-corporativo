package br.com.fiap.gestao_transporte_corporativo.repository;

import br.com.fiap.gestao_transporte_corporativo.model.UsoTransporteFuncionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UsoTransporteFuncionarioRepository extends JpaRepository<UsoTransporteFuncionario, Long> {

    Page<UsoTransporteFuncionario> findByTransporte_Id(Long transporteId, Pageable paginacao);

    Page<UsoTransporteFuncionario> findByFuncionario_Id(Long funcionarioId, Pageable paginacao);

    Page<UsoTransporteFuncionario> findByDataUsoBetween(LocalDate dataInicial, LocalDate dataFinal, Pageable paginacao);

}
