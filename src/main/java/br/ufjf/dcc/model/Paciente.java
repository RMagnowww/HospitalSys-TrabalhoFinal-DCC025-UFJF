package br.ufjf.dcc.model;
import br.ufjf.dcc.model.enums.PerfilUsuario;
import java.util.ArrayList;
import java.util.List;

public class Paciente extends Usuario{
    private static final long serialVersionUID = 1L;
    private String dataNascimento;
    private String tipoSanguineo;
    private Endereco endereco;

    private boolean aceitaVisitas;

    private List<Consulta> historicoConsultas;
    private List<Exame> exames;


    public Paciente(String nome, String cpf, String telefone, String email, String senha, Endereco endereco, String dataNascimento, String tipoSanguineo) {
        super(nome, cpf, telefone, email, senha, PerfilUsuario.PACIENTE);
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.tipoSanguineo = tipoSanguineo;
        this.aceitaVisitas = true;

        this.historicoConsultas = new ArrayList<>();
        this.exames = new ArrayList<>();
    }
    public String getDataNascimento() { 
        return dataNascimento; 
    }
    public void setDataNascimento(String dataNascimento) { 
        this.dataNascimento = dataNascimento; 
    }
    public String getTipoSanguineo() { 
        return tipoSanguineo; 
    }
    public void setTipoSanguineo(String tipoSanguineo) { 
        this.tipoSanguineo = tipoSanguineo; 
    }
    public boolean isAceitaVisitas() { 
        return aceitaVisitas;
    }
    public void setAceitaVisitas(boolean aceitaVisitas) { 
        this.aceitaVisitas = aceitaVisitas; 
    }
    public Endereco getEndereco() { 
        return endereco; 
    }
    public void setEndereco(Endereco endereco) { 
        this.endereco = endereco; 
    }

    public void adicionarConsulta(Consulta consulta) {
        this.historicoConsultas.add(consulta);
    }
    public List<Consulta> getHistoricoConsultas() {
        return historicoConsultas;
    }
    public void adicionarExame(Exame exame) {
        this.exames.add(exame);
    }
    public List<Exame> getExames() {
        return exames;
    }
}

