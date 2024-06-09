package com.ms.resultado.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "resultados")
public class Resultado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_sessao")
    private Long idSessao;

    @Column(name = "votos_aprovados")
    private int votosAprovados;

    @Column(name = "votos_reprovados")
    private int votosReprovados;

}
