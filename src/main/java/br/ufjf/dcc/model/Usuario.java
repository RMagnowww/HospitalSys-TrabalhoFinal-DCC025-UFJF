package br.ufjf.dcc.model;
import br.ufjf.dcc.model.enums.PerfilUsuario;
import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private String cpf;
    private String senha;
    private String telefone;
    private String email;
    private PerfilUsuario perfil;
    
    public Usuario(String nome, String cpf, String telefone, String email, String senha, PerfilUsuario perfil){
        setNome(nome);
        setCpf(cpf);
        setSenha(senha);
        this.telefone = telefone;
        this.email = email;
        this.perfil = perfil;
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
    public PerfilUsuario getPerfil() { 
        return perfil; 
    }
    
}
