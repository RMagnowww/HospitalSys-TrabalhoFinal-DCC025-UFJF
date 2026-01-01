package br.ufjf.dcc.model;

import br.ufjf.dcc.model.enums.TipoDocumento;
import java.time.format.DateTimeFormatter;

public class Receita extends DocumentoMedico{


    private String medicamentos;

    public Receita(Medico medico, Paciente paciente, String medicamentos) {
        super(medico, paciente, TipoDocumento.RECEITA);
        setMedicamentos(medicamentos);
    }
    @Override
    public String gerarConteudo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append("=== RECEITA MÉDICA ===\n");
        sb.append("Data: ").append(getData().format(formatter)).append("\n");
        sb.append("Paciente: ").append(getPaciente().getNome()).append("\n");
        sb.append("Médico: ").append(getMedico().getNome()).append("\n\n");
        sb.append("PRESCRIÇÃO:\n");
        sb.append(medicamentos).append("\n");
        return sb.toString();
    }
    public String getMedicamentos() {
        return medicamentos;
    }
    public void setMedicamentos(String medicamentos) {
        if (medicamentos == null || medicamentos.trim().isEmpty()) {
            throw new IllegalArgumentException("A prescrição de medicamentos não pode estar vazia.");
        }
        this.medicamentos = medicamentos;
    }
    @Override
    public String toString() {
        return getTipo().toString() + " - Paciente: " + getPaciente().getNome() + " - Médico: " + getMedico().getNome() + " - " + getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}