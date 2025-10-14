package br.com.fiap.gestao_transporte_corporativo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "TBL_TRANSPORTE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Transporte {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRANSPORTE")
    @SequenceGenerator(name = "SEQ_TRANSPORTE", sequenceName = "SEQ_TRANSPORTE", allocationSize = 1)
    @Column(name = "ID_TRANSPORTE")
    private Long id;

    private String modelo;

    @Column(name = "TIPO_COMBUSTIVEL")
    private String tipoCombustivel;

    @Column(name = "KM_POR_LITRO")
    private float kmPorLitro;

    @Column(name = "ULTIMA_MANUTENCAO")
    private LocalDate ultimaManutencao;
}