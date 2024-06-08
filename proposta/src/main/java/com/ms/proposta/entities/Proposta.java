package com.ms.proposta.entities;

import com.ms.proposta.Enum.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "propostas")
public class Proposta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "idFuncionario")
    private Long idFuncionario;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @Column(name = "dataProposta")
    private Date dataProposta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proposta proposta)) return false;
        return Objects.equals(getId(), proposta.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Proposta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataProposta=" + dataProposta +
                '}';
    }
}
