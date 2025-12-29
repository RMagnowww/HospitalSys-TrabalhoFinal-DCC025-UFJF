package br.ufjf.dcc.model;
import java.io.Serializable;
import br.ufjf.dcc.model.enums.PerfilUsuario;

public class Secretario extends Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    private String matricula;

    public Secretario(String nome, String cpf, String email, String telefone, String senha, String matricula) {
        super(nome, cpf, email, telefone, senha, PerfilUsuario.SECRETARIO);
        setMatricula(matricula);
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) {
        if(matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Matricula do funcionario é obrigatoria");
        }
        this.matricula = matricula;
    }

    // metodos utilitarios
    // logica deve ficar no SecretarioController

}
