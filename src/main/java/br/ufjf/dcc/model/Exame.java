/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */
    
package br.ufjf.dcc.model;
import br.ufjf.dcc.model.enums.TipoDocumento;
import java.time.format.DateTimeFormatter;

public class Exame extends DocumentoMedico {

    private String nomeExame;
    private String resultado;


    public Exame(Medico medico, Paciente paciente, String nomeExame, String resultado) {
        super(medico,paciente,TipoDocumento.EXAME);
        setNomeExame(nomeExame);
        setResultado(resultado);
    }
    
    @Override
    public String gerarConteudo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("=== RESULTADO DE EXAME ===\n");
        sb.append("Exame: ").append(nomeExame).append("\n");
        sb.append("Data: ").append(getData().format(formatter)).append("\n");
        sb.append("Paciente: ").append(getPaciente().getNome()).append("\n");
        sb.append("Solicitado por: ").append(getMedico().getNome()).append("\n");
        sb.append("--------------------------\n");
        sb.append("RESULTADO:\n").append(resultado).append("\n");
        return sb.toString();
    }

    public void setResultado(String resultado) {
        if (resultado == null || resultado.trim().isEmpty()) {
            throw new IllegalArgumentException("O resultado não pode ser vazio.");
        }
        this.resultado = resultado;
    }

    public String getNomeExame() {
        return nomeExame;
    }
    public void setNomeExame(String nomeExame) {
        if (nomeExame == null || nomeExame.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do exame é obrigatório.");
        }
        this.nomeExame = nomeExame;
    }
    public String getResultado() {
        return resultado;
    }
    @Override
    public String toString() {
        return getTipo().toString() + ": " + nomeExame + " - Paciente: " + getPaciente().getNome() + " - Médico: " + getMedico().getNome() + " - " + getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")); 
    }
}