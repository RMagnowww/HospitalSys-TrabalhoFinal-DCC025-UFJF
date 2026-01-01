package br.ufjf.dcc.model;
import br.ufjf.dcc.model.enums.TipoDocumento;
import java.time.LocalDateTime;

public abstract class DocumentoMedico {
    private Medico medico;
    private Paciente paciente;
    private LocalDateTime data;
    private TipoDocumento tipo;

    public DocumentoMedico(Medico medico, Paciente paciente, TipoDocumento tipo){
        setMedico(medico);
        setPaciente(paciente);
        this.data = LocalDateTime.now();
        this.tipo = tipo;
    }
    public Medico getMedico(){
        return medico;
    }
    public void setMedico(Medico medico){
        if (medico == null) throw new IllegalArgumentException("Médico é obrigatório.");
            this.medico = medico;
    }
    public Paciente getPaciente(){
        return paciente;
    }
    public void setPaciente(Paciente paciente){
        if (paciente == null) throw new IllegalArgumentException("Paciente é obrigatório.");
            this.paciente = paciente;
    }
    public LocalDateTime getData(){
        return data;
    }
    public void setData(LocalDateTime data){
        this.data = data;
    }
    public TipoDocumento getTipo(){
        return tipo;
    }
    public void setTipo(TipoDocumento tipo){
        this.tipo = tipo;
    }
}
