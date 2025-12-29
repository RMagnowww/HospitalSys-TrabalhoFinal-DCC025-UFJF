package br.ufjf.dcc.model;
import br.ufjf.dcc.model.enums.PerfilUsuario;

public class Secretario extends Usuario{
    private static final long serialVersionUID = 1L;
    private String matricula;

    public Secretario(String nome, String cpf, String telefone, String email, String senha, String matricula) {
        super(nome, cpf, telefone, email, senha, PerfilUsuario.SECRETARIO);
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
