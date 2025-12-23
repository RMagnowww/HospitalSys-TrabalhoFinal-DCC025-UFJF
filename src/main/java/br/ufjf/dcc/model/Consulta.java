package br.ufjf.dcc.model;

public class Consulta {
    private String data;
    private String horario;
    private String descricao;
    private Paciente paciente;
    private Medico medico;

    public Consulta(String data, String horario, String descricao, Paciente paciente, Medico medico) {
        this.data = data;
        this.horario = horario;
        this.descricao = descricao;
        this.paciente = paciente;
        this.medico = medico;
    }

}
