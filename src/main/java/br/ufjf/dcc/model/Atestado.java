package br.ufjf.dcc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Atestado implements DocumentoMedico{
    private Medico medico;
    private Paciente paciente;
    private LocalDateTime dataEmissao;
    private int diasAfastamento;
    private String descricao;

    public Atestado(Medico medico, Paciente paciente, int diasAfastamento, String descricao) {
        setMedico(medico);
        setPaciente(paciente);
        setDiasAfastamento(diasAfastamento);
        setDescricao(descricao);
        this.dataEmissao = LocalDateTime.now(); // data gerada no momento da criacao
    }

    @Override
    public String getTipo() {
        return "Atestado Médico";
    }

    @Override
    public String gerarConteudo(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("=== ATESTADO MÉDICO===\n");
        sb.append("Emissão: ").append(dataEmissao.format(formatter)).append("\n");
        sb.append("Paciente: ").append(paciente.getNome()).append(" (CPF: ").append(paciente.getCpf()).append(")\n");
        sb.append("Médico Responsável: Dr(a). ").append(medico.getNome()).append(" (CRM: ").append(medico.getCrm()).append(")\n\n");
        sb.append("Declaro para os devidos fins que o paciente acima necessita de ").append(diasAfastamento).append(" dias de afastamento.\n");
        sb.append("Observações: ").append(descricao).append("\n");
        return sb.toString();
    }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) {
        if(medico == null) {
            throw new IllegalArgumentException("Médico é obrigatório");
        }
        this.medico = medico;
    }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) {
        if(paciente == null) {
            throw new IllegalArgumentException("Paciente é obrigatório");
        }
        this.paciente = paciente;
    }

    public int getDiasAfastamento() { return diasAfastamento; }
    public void setDiasAfastamento(int diasAfastamento) {
        if(diasAfastamento < 0){
            throw new IllegalArgumentException("Dias de afastamento não podem ser negativos");
        }
        this.diasAfastamento = diasAfastamento;
    }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataEmissao() { return dataEmissao; }
}

