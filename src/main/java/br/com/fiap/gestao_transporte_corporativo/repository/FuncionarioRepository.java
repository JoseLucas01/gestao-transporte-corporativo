package br.com.fiap.gestao_transporte_corporativo.repository;

import br.com.fiap.gestao_transporte_corporativo.model.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

    Optional<Funcionario> findByNome(String nome);

    Page<Funcionario> findByTransporteAlternativo(Boolean transporteAlternativo, Pageable paginacao);

    Page<Funcionario> findByVeiculoCorporativo(Boolean veiculoCorporativo, Pageable paginacao);

}
