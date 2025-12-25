package br.ufjf.dcc.model;

import java.util.ArrayList;
import java.util.List;

public class Prontuario {

    private Paciente paciente;

    private List<String> historicoDoencas;
    private List<String> alergias;
    private List<String> anotacoesMedicas;

    public Prontuario(Paciente paciente) {
        setPaciente(paciente);
        this.historicoDoencas = new ArrayList<>();
        this.alergias = new ArrayList<>();
        this.anotacoesMedicas = new ArrayList<>();
    }

    public void adicionarDoenca(String doenca) {
        if (doenca != null && !doenca.trim().isEmpty()) {
            historicoDoencas.add(doenca);
        }
    }

    public void adicionarAlergia(String alergia) {
        if (alergia != null && !alergia.trim().isEmpty()) {
            alergias.add(alergia);
        }
    }

    public void adicionarAnotacao(String anotacao) {
        if (anotacao != null && !anotacao.trim().isEmpty()) {
            String registro = java.time.LocalDateTime.now().toString() + ": " + anotacao;
            anotacoesMedicas.add(registro);
        }
    }

    public String getHistoricoCompleto() {
        StringBuilder sb = new StringBuilder();
        sb.append("PRONTUÁRIO: ").append(paciente.getNome()).append("\n");

        sb.append("\n[ALERGIAS]\n");
        if (alergias.isEmpty()) sb.append("- Nenhuma alergia registrada.\n");
        else for (String a : alergias) sb.append("- ").append(a).append("\n");

        sb.append("\n[HISTÓRICO DE DOENÇAS]\n");
        if (historicoDoencas.isEmpty()) sb.append("- Nenhum histórico registrado.\n");
        else for (String h : historicoDoencas) sb.append("- ").append(h).append("\n");

        sb.append("\n[EVOLUÇÃO CLÍNICA]\n");
        if (anotacoesMedicas.isEmpty()) sb.append("- Nenhuma anotação.\n");
        else for (String obs : anotacoesMedicas) sb.append(obs).append("\n");

        return sb.toString();
    }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("O prontuário deve estar associado a um paciente.");
        }
        this.paciente = paciente;
    }

    public List<String> getHistoricoDoencas() { return historicoDoencas; }

    public List<String> getAlergias() { return alergias; }

    public List<String> getAnotacoesMedicas() { return anotacoesMedicas; }
}