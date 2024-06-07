package com.ms.proposta.entities;

import com.ms.proposta.Enum.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @Column(name = "dataProposta")
    private Date dataProposta;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "tempoVoto")
    private Long tempoVoto;

    @Transient
    private String tempoRestante;

    public String getTempoRestante() {
        if (status == Status.INATIVO) {
            return "00:00:00";
        }
        long atual = new Date().getTime();
        long fim = dataProposta.getTime() + tempoVoto;
        long tempoRestante = Math.max(0, fim - atual);

        long horas = TimeUnit.MILLISECONDS.toHours(tempoRestante);
        long minutos = TimeUnit.MILLISECONDS.toMinutes(tempoRestante) % 60;
        long segundos = TimeUnit.MILLISECONDS.toSeconds(tempoRestante) % 60;
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    public static Long converterTempo(String tempoVoto) {
        String[] parts = tempoVoto.split(":");
        long hours = Long.parseLong(parts[0]);
        long minutes = Long.parseLong(parts[1]);
        return TimeUnit.HOURS.toMillis(hours) + TimeUnit.MINUTES.toMillis(minutes);
    }

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
                ", status=" + status +
                ", tempoVoto=" + tempoVoto +
                ", tempoRestante=" + getTempoRestante() +
                '}';
    }
}
