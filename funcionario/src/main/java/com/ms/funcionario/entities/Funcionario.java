package com.ms.funcionario.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Table(name = "funcionario")
@Entity(name = "funcionario")
@Data
@NoArgsConstructor
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_nascimento", nullable = false)
    private String dataNasc;
    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    private String cpf;
    @Column(name = "sexo", nullable = false, length = 1)
    private String sexo;
}