package br.com.fiap.gestao_transporte_corporativo.repository;

import br.com.fiap.gestao_transporte_corporativo.model.Recompensa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecompensaRepository extends JpaRepository<Recompensa, Long> {
    Page<Recompensa> findByFuncionario_Id(Long funcionarioId, Pageable paginacao);
}
