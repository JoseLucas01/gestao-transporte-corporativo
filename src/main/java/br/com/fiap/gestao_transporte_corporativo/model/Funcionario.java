package br.com.fiap.gestao_transporte_corporativo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_FUNCIONARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FUNCIONARIO")
    @SequenceGenerator(name = "SEQ_FUNCIONARIO", sequenceName = "SEQ_FUNCIONARIO", allocationSize = 1)
    @Column(name = "ID_FUNCIONARIO")
    private Long id;

    private String nome;

    @Column(name = "TRANSPORTE_ALTERNATIVO")
    private Boolean transporteAlternativo;

    @Column(name = "VEICULO_CORPORATIVO")
    private Boolean veiculoCorporativo;

}