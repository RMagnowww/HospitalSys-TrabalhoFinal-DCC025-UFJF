package br.ufjf.dcc.model;

public class Usuario {
    private final String nome;
    private final String cpf;
    private String senha;
    private String telefone;
    private String email;
    
    public Usuario(String nome, String cpf, String senha, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.telefone = telefone;
        this.email = email;
    }
    public String getNome() {
        return this.nome;
    }
    public String getCpf() {
        return this.cpf;
    }
    public String getTelefone() {
        return this.telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public string getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {          
        return this.senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;     
    }
}
