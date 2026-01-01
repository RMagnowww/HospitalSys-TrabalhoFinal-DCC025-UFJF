package br.ufjf.dcc.model;
import br.ufjf.dcc.model.enums.TipoDocumento;
import java.time.format.DateTimeFormatter;

public class Atestado extends DocumentoMedico{
    private int diasAfastamento;
    private String descricao;

    public Atestado(Medico medico, Paciente paciente, int diasAfastamento, String descricao) {
        super(medico, paciente, TipoDocumento.ATESTADO);
        setDiasAfastamento(diasAfastamento);
        setDescricao(descricao);

    }
    @Override
    public String gerarConteudo(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("=== ATESTADO MÉDICO===\n");
        sb.append("Emissão: ").append(getData().format(formatter)).append("\n");
        sb.append("Paciente: ").append(getPaciente().getNome()).append(" (CPF: ").append(getPaciente().getCpf()).append(")\n");
        sb.append("Médico Responsável: ").append(getMedico().getNome()).append(" (CRM: ").append(getMedico().getCrm()).append(")\n\n");
        sb.append("Declaro para os devidos fins que o paciente acima necessita de ").append(diasAfastamento).append(" dias de afastamento.\n");
        sb.append("Observações: ").append(descricao).append("\n");
        return sb.toString();
    }
    public int getDiasAfastamento() {
        return diasAfastamento;
    }
    public void setDiasAfastamento(int diasAfastamento){
        if(diasAfastamento < 0){
            throw new IllegalArgumentException("Dias de afastamento não podem ser negativos");
        }
        this.diasAfastamento = diasAfastamento;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return getTipo().toString() + " - Paciente: " + getPaciente().getNome() + " - Médico: " + getMedico().getNome() + " - " + getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}

