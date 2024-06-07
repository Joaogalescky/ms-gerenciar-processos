package com.ms.resultado.entities;


import com.ms.resultado.enums.Apuracao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Table(name = "voto")
@Entity(name = "voto")
@Data
@NoArgsConstructor
public class Voto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "idProposta")
    private Long idProposta;

    @Column(name = "idFunc")
    private Long idFunc;

    @Enumerated(EnumType.STRING)
    @Column(name = "apuracao")
    private Apuracao apuracao;
}
