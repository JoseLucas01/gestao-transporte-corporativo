package br.com.fiap.gestao_transporte_corporativo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_META_AMBIENTAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MetaAmbiental {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_META_AMBIENTAL")
    @SequenceGenerator(name = "SEQ_META_AMBIENTAL", sequenceName = "SEQ_META_AMBIENTAL", allocationSize = 1)
    @Column(name = "ID_META_AMBIENTAL")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_TRANSPORTE")
    private Transporte transporte;

    private String descricao;

    @Column(name = "MAX_KM_POR_MES")
    private float maxKmPorMes;

}
