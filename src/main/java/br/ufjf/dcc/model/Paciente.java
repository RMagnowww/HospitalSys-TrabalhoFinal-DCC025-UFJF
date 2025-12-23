package br.ufjf.dcc.model;

public class Paciente extends Usuario{
    private String endereco;

    public Paciente(String nome, String cpf, String senha, String telefone, String email, String endereco) {
        super(nome, cpf, senha, telefone, email);
        this.endereco = endereco;
    }
    public String getEndereco() {
        return this.endereco;
    } 
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }   
}
