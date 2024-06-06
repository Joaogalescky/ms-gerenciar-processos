package com.ms.funcionario.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Table(name = "funcionario")
@Entity(name = "funcionario")
@Data
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;
    @Column(name = "data_nascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNasc;
    @Column(name = "cpf", nullable = false, length = 11) // 111 111 111 11
    private String cpf;
    @Column(name = "sexo", nullable = false)
    private Enum sexo;
}