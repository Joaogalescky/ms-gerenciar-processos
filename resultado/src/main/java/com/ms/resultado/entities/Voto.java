package com.ms.resultado.entities;


import com.ms.resultado.enums.Escolha;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Table(name = "voto")
@Entity(name = "voto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "idSessao")
    private Long idSessao;

    @Column(name = "idFunc")
    private Long idFunc;

    @Enumerated(EnumType.STRING)
    @Column(name = "escolha")
    private Escolha escolha;
}
