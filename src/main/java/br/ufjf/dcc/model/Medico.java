package br.ufjf.dcc.model;

import java.util.ArrayList;
import java.util.List;

public class Medico extends Usuario {

    private String crm; // registro
    private String especialidade; //



    private String horarioInicioExpediente; //14:00
    private String horarioFimExpediente;    //18:00



    private List<Consulta> agendaConsultas;

    public Medico(String nome, String cpf, String senha, String telefone, String email, String crm, String especialidade) {
        super(nome, cpf, senha, telefone, email);
        setCrm(crm);
        setEspecialidade(especialidade);
        this.agendaConsultas = new ArrayList<>();
    }



    public String getCrm() { return crm; }
    public void setCrm(String crm) {
        if (crm == null || crm.trim().isEmpty()) {
            throw new IllegalArgumentException("O CRM do médico é obrigatório.");
        }
        this.crm = crm;
    }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) {
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new IllegalArgumentException("A especialidade é obrigatória.");
        }
        this.especialidade = especialidade;
    }

    public String getHorarioInicioExpediente() { return horarioInicioExpediente; }
    public void setHorarioInicioExpediente(String horarioInicioExpediente) { this.horarioInicioExpediente = horarioInicioExpediente; }

    public String getHorarioFimExpediente() {return horarioFimExpediente; }
    public void setHorarioFimExpediente(String horarioFimExpediente) { this.horarioFimExpediente = horarioFimExpediente; }


    public List<Consulta> getAgendaConsultas() {
        return agendaConsultas;
    }

    public void adicionarConsultaAgenda(Consulta consulta) {
        this.agendaConsultas.add(consulta);
    }
}