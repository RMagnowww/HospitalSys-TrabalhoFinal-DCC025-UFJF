package br.ufjf.dcc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receita implements DocumentoMedico{

    private Medico medico;
    private Paciente paciente;
    private LocalDateTime dataEmissao;
    private String medicamentos;

    public Receita(Medico medico, Paciente paciente, String medicamentos) {
        setMedico(medico);
        setPaciente(paciente);
        setMedicamentos(medicamentos);
        this.dataEmissao = LocalDateTime.now();
    }

    @Override
    public String getTipo() {
        return "Receita Médica";
    }

    @Override
    public String gerarConteudo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append("=== RECEITA MÉDICA ===\n");
        sb.append("Data: ").append(dataEmissao.format(formatter)).append("\n");
        sb.append("Paciente: ").append(paciente.getNome()).append("\n");
        sb.append("Dr(a): ").append(medico.getNome()).append("\n\n");
        sb.append("PRESCRIÇÃO:\n");
        sb.append(medicamentos).append("\n");
        return sb.toString();
    }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) {
        if (medico == null) throw new IllegalArgumentException("Médico é obrigatório.");
        this.medico = medico;
    }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) {
        if (paciente == null) throw new IllegalArgumentException("Paciente é obrigatório.");
        this.paciente = paciente;
    }

    public String getMedicamentos() { return medicamentos; }
    public void setMedicamentos(String medicamentos) {
        if (medicamentos == null || medicamentos.trim().isEmpty()) {
            throw new IllegalArgumentException("A prescrição de medicamentos não pode estar vazia.");
        }
        this.medicamentos = medicamentos;
    }

    public LocalDateTime getDataEmissao() { return dataEmissao; }
}