package br.ufjf.dcc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Exame implements DocumentoMedico {

    private String nomeExame;
    private String resultado;
    private Medico medicoSolicitante;
    private Paciente paciente;
    private LocalDateTime dataRealizacao;
    private boolean concluido;

    public Exame(String nomeExame, Medico medicoSolicitante, Paciente paciente) {
        setNomeExame(nomeExame);
        setMedicoSolicitante(medicoSolicitante);
        setPaciente(paciente);
        this.dataRealizacao = LocalDateTime.now();
        this.concluido = false;
        this.resultado = "Aguardando resultado...";
    }

    @Override
    public String getTipo() {
        return "Exame Laboratorial";
    }

    @Override
    public String gerarConteudo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("=== RESULTADO DE EXAME ===\n");
        sb.append("Exame: ").append(nomeExame).append("\n");
        sb.append("Data: ").append(dataRealizacao.format(formatter)).append("\n");
        sb.append("Paciente: ").append(paciente.getNome()).append("\n");
        sb.append("Solicitado por: Dr(a). ").append(medicoSolicitante.getNome()).append("\n");
        sb.append("Status: ").append(concluido ? "Concluído" : "Pendente").append("\n");
        sb.append("--------------------------\n");
        sb.append("RESULTADO:\n").append(resultado).append("\n");
        return sb.toString();
    }

    public void processarResultado(String resultadoFinal) {
        if (resultadoFinal == null || resultadoFinal.trim().isEmpty()) {
            throw new IllegalArgumentException("O resultado não pode ser vazio.");
        }
        this.resultado = resultadoFinal;
        this.concluido = true;
    }

    public String getNomeExame() { return nomeExame; }
    public void setNomeExame(String nomeExame) {
        if (nomeExame == null || nomeExame.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do exame é obrigatório.");
        }
        this.nomeExame = nomeExame;
    }

    public String getResultado() { return resultado; }

    public Medico getMedicoSolicitante() { return medicoSolicitante; }
    public void setMedicoSolicitante(Medico medicoSolicitante) {
        if (medicoSolicitante == null) throw new IllegalArgumentException("Médico solicitante é obrigatório.");
        this.medicoSolicitante = medicoSolicitante;
    }

    public Paciente getPaciente() { return paciente; }

    public void setPaciente(Paciente paciente) {
        if (paciente == null) throw new IllegalArgumentException("Paciente é obrigatório.");
        this.paciente = paciente;
    }

    public boolean isConcluido() {
        return concluido;
    }

    @Override
    public String toString() {
        return nomeExame + " - " + (concluido ? "Concluído" : "Pendente");
    }
}