package br.ufjf.dcc.model;
import br.ufjf.dcc.model.enums.*;

import java.util.ArrayList;
import java.util.List;

public class Medico extends Usuario {
    private static final long serialVersionUID = 1L;

    private String crm; // registro
    private String especialidade; //

    private StatusMedico atividade; // ATIVO ou INATIVO
    private String horarioInicioExpediente; //14:00
    private String horarioFimExpediente;    //18:00
    private int duracaoConsulta;

    private List<Consulta> agendaConsultas;

    public Medico(String nome, String cpf, String telefone, String email, String senha, String crm, String especialidade) {
        super(nome, cpf, telefone, email, senha, PerfilUsuario.MEDICO);
        setCrm(crm);
        setEspecialidade(especialidade);
        this.atividade = StatusMedico.ATIVO;
        this.agendaConsultas = new ArrayList<>();
        setDuracaoConsulta(0);
    }

    public String getCrm() { 
        return crm; 
    }
    public void setCrm(String crm) {
        if (crm == null || crm.trim().isEmpty()) {
            throw new IllegalArgumentException("O CRM do médico é obrigatório.");
        }
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade; 
    }
    public void setEspecialidade(String especialidade) {
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new IllegalArgumentException("A especialidade é obrigatória.");
        }
        this.especialidade = especialidade;
    }

    public String getHorarioInicioExpediente() {
        return horarioInicioExpediente; 
    }
    public void setHorarioInicioExpediente(String horarioInicioExpediente) {
        this.horarioInicioExpediente = horarioInicioExpediente; 
    }
    public String getHorarioFimExpediente() {
        return horarioFimExpediente; 
    }
    public void setHorarioFimExpediente(String horarioFimExpediente) {
        this.horarioFimExpediente = horarioFimExpediente; 
    }
     public int getDuracaoConsulta() {
        return duracaoConsulta; 
    }
    public void setDuracaoConsulta(int duracaoConsulta) {
        this.duracaoConsulta = duracaoConsulta; 
    }
    public StatusMedico getAtividade(){
        return atividade;
    }
    public void setAtividade(StatusMedico atividade){
        this.atividade = atividade;
    }

    public List<Consulta> getAgendaConsultas() {
        return agendaConsultas;
    }

    public void adicionarConsultaAgenda(Consulta consulta) {
        this.agendaConsultas.add(consulta);
    }
    @Override
    public String toString(){
        return getNome() + " - CPF: " + getCpf() + " - " + getEspecialidade();
    }
}