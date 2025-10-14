package br.com.fiap.gestao_transporte_corporativo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_RECOMPENSA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RECOMPENSA")
    @SequenceGenerator(name = "SEQ_RECOMPENSA", sequenceName = "SEQ_RECOMPENSA", allocationSize = 1)
    @Column(name = "ID_RECOMPENSA")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_FUNCIONARIO")
    private Funcionario funcionario;

    private String descricao;

    private float valor;
}
