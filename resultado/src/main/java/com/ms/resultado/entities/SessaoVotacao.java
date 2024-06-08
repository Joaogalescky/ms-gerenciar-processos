package com.ms.resultado.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ms.resultado.enums.StatusSessao;
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
@Table(name = "sessaoVotacao")
public class SessaoVotacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "idProposta")
    private Long idProposta;

    @Column(name = "tempoVotacao")
    private Long tempoVotacao;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusSessao status;

    @Column(name = "horaInicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "America/Sao_Paulo")
    private Date horaInicio;

    @Transient
    private String tempoRestante;

    public String getTempoRestante() {
        if (status == StatusSessao.INATIVO) {
            return "00:00:00";
        }
        long atual = new Date().getTime();
        long fim = horaInicio.getTime() + tempoVotacao;
        long tempoRestante = Math.max(0, fim - atual);

        long horas = TimeUnit.MILLISECONDS.toHours(tempoRestante);
        long minutos = TimeUnit.MILLISECONDS.toMinutes(tempoRestante) % 60;
        long segundos = TimeUnit.MILLISECONDS.toSeconds(tempoRestante) % 60;
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    public static Long converterTempo(String tempoVotacao) {
        String[] parts = tempoVotacao.split(":");
        long hours = Long.parseLong(parts[0]);
        long minutes = Long.parseLong(parts[1]);
        return TimeUnit.HOURS.toMillis(hours) + TimeUnit.MINUTES.toMillis(minutes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessaoVotacao sessaoVotacao)) return false;
        return Objects.equals(getId(), sessaoVotacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "SessaoVotacao{" +
                "id=" + id +
                ", idProposta=" + idProposta +
                ", tempoVotacao=" + tempoVotacao +
                ", status=" + status +
                ", horaInicio=" + horaInicio +
                ", tempoRestante='" + getTempoRestante() + '\'' +
                '}';
    }
}
