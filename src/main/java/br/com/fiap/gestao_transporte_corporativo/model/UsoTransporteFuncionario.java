package br.com.fiap.gestao_transporte_corporativo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "TBL_USO_TRANSPORTE_FUNCIONARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UsoTransporteFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USO_TRANSPORTE_FUNCIONARIO")
    @SequenceGenerator(name = "SEQ_USO_TRANSPORTE_FUNCIONARIO", sequenceName = "SEQ_USO_TRANSPORTE_FUNCIONARIO", allocationSize = 1)
    @Column(name = "ID_USO_TRANSPORTE_FUNCIONARIO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_TRANSPORTE")
    private Transporte transporte;

    @ManyToOne
    @JoinColumn(name = "ID_FUNCIONARIO")
    private Funcionario funcionario;

    @Column(name = "DATA_USO")
    private LocalDate dataUso;

    @Column(name = "KM_RODADO")
    private float kmRodado;

}
