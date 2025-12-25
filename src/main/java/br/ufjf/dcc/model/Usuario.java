package br.ufjf.dcc.model;

public abstract class Usuario {
    private String nome;
    private String cpf;
    private String senha;
    private String telefone;
    private String email;
    private Endereco endereco;
    
    public Usuario(String nome, String cpf, String senha, String telefone, String email) {
        setNome(nome);
        setCpf(cpf);
        setSenha(senha);
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        if(nome == null || nome.trim().length() <= 1){
            throw new IllegalArgumentException("O nome deve ter mais de um caractere");
        }
        this.nome= nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf){
        if(cpf == null || cpf.trim().isEmpty()){
            throw new IllegalArgumentException("CPF inválido ou vazio.");
        }
        this.cpf = cpf;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        if(senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("A senha é obrigatória.");
        }
        this.senha = senha;
    }

    public String getTelefone() {
        return this.telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }
}
