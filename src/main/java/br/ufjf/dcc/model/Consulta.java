/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */
    
package br.ufjf.dcc.model;

import br.ufjf.dcc.model.enums.StatusConsulta;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Consulta {
    private LocalDateTime dataHora;
    private StatusConsulta status;
    private String descricao;
    private Paciente paciente;
    private Medico medico;

    public Consulta(LocalDateTime dataHora, Paciente paciente, Medico medico,StatusConsulta status) {
        setStatus(status);
        setPaciente(paciente);
        setMedico(medico);
        setDataHora(dataHora);
    }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) {
        if(medico == null) {
            throw new IllegalArgumentException("A consulta deve ter um médico responsável");
        }
        this.medico = medico;
    }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) {
        if(paciente == null) {
            throw new IllegalArgumentException("A consulta deve ter um paciente.");
        }
        this.paciente = paciente;
    }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) {
        if (dataHora == null) {
            throw new IllegalArgumentException("A data e hora da consulta são obrigatórias.");
        }
        /*if (dataHora.isBefore(LocalDateTime.now()) && this.status != StatusConsulta.REALIZADA) {
            throw new IllegalArgumentException("Não é possível agendar consultas para o passado.");
        }*/
        this.dataHora = dataHora;
    }

    public StatusConsulta getStatus() { return status; }
    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString(){
        return "Consulta: " + dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + " - Medico: " + medico.getNome() + " - Paciente: " + paciente.getNome() + " [" + status + "]";
    }
}
