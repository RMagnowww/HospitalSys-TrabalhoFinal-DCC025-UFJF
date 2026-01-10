/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */
    
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
        setTelefone(telefone);
        setEmail(email);
        this.perfil = perfil;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
            if (nome == null || nome.trim().length() <= 1) {
                throw new IllegalArgumentException("O nome deve ter mais de um caractere");
            }
            this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser vazio ou nulo.");
        }

        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos numéricos.");
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        String cpfFormatado = String.format("%s.%s.%s-%s", 
            cpf.substring(0, 3),   
            cpf.substring(3, 6),  
            cpf.substring(6, 9),  
            cpf.substring(9));     

        this.cpf = cpfFormatado;  
}

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
            if (senha == null || senha.trim().isEmpty()) {
                throw new IllegalArgumentException("A senha é obrigatória.");
            }
            this.senha = senha;
    }

    public String getTelefone() {
        return this.telefone;
    }
    
    public void setTelefone(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser vazio ou nulo.");
        }

        telefone = telefone.replaceAll("[^0-9]", "");

   
        if (telefone.length() != 11) {
            throw new IllegalArgumentException("Telefone deve conter 11 dígitos numéricos.");
        }

     
        this.telefone = String.format("(%s)%s-%s", 
            telefone.substring(0, 2),   
            telefone.substring(2, 7),   
            telefone.substring(7));     
}

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio ou nulo.");
        }

        if (!email.matches("^[a-zA-Z0-9]+@(paciente|medico|admin)\\.com$")) {
            throw new IllegalArgumentException("Email deve seguir o formato: algo@paciente.com, algo@medico.com ou algo@admin.com");
        }

        this.email = email;  
}


    public PerfilUsuario getPerfil() { 
        return perfil; 
    }
    
}
