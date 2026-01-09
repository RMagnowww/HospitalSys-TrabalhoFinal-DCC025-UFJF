/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */
    
package br.ufjf.dcc.model;
import br.ufjf.dcc.model.enums.PerfilUsuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
        setDataNascimento(dataNascimento);
        this.tipoSanguineo = tipoSanguineo;
        this.aceitaVisitas = true;

        this.historicoConsultas = new ArrayList<>();
        this.exames = new ArrayList<>();
    }
    public String getDataNascimento() { 
        return dataNascimento; 
    }
    public void setDataNascimento(String dataNascimento) { 
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataNascimento, formatter);

            if(data.isAfter(LocalDate.now())){
                throw new IllegalArgumentException("Data de nascimento não pode ser no futuro");
            }
            this.dataNascimento = dataNascimento;
        } catch (DateTimeParseException e){
            JOptionPane.showMessageDialog(null,
                "Data invalida. use o Formato dd/MM/yyyy",
                "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(null,
                e.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
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
    @Override
    public String toString(){
        return getNome() + " - CPF: " + getCpf();
    }
}

