package br.ufjf.dcc.controller;

import br.ufjf.dcc.model.*;
import br.ufjf.dcc.model.enums.StatusConsulta;
import br.ufjf.dcc.model.enums.StatusMedico;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;


public class AgendamentoController {
    public static void agendarConsulta(Paciente paciente, Medico medico, LocalDateTime dataHora, String descricao) {
        try {
            if (medico.getAtividade() != StatusMedico.ATIVO) {
                JOptionPane.showMessageDialog(new JFrame(), 
                    "Este médico não está disponível para consultas!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (dataHora.isBefore(LocalDateTime.now())) {
                JOptionPane.showMessageDialog(new JFrame(), 
                    "Não é possível agendar consultas para datas passadas!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!horarioDisponivel(medico, dataHora)) {
                JOptionPane.showMessageDialog(new JFrame(), 
                    "Este horário já está ocupado!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Consulta consulta = new Consulta(dataHora, paciente, medico, StatusConsulta.AGENDADA);
            consulta.setDescricao(descricao != null ? descricao : "");

            Persistencia.salvarConsultas(consulta);

            JOptionPane.showMessageDialog(new JFrame(), 
                "Consulta agendada com sucesso!", 
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(), 
                "Erro ao agendar consulta: " + ex.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void cancelarConsulta(Consulta consulta) {
        try {
            if (consulta.getStatus() == StatusConsulta.REALIZADA) {
                JOptionPane.showMessageDialog(new JFrame(), 
                    "Não é possível cancelar uma consulta já realizada!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Persistencia.deletarConsulta(consulta);
            
            JOptionPane.showMessageDialog(new JFrame(), 
                "Consulta cancelada com sucesso!", 
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(), 
                "Erro ao cancelar consulta: " + ex.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void realizarConsulta(Consulta consulta, String observacoes) {
        try {
            Persistencia.deletarConsulta(consulta);

            consulta.setStatus(StatusConsulta.REALIZADA);
            if (observacoes != null && !observacoes.trim().isEmpty()) {
                consulta.setDescricao(observacoes);
            }

            Persistencia.salvarConsultas(consulta);

            JOptionPane.showMessageDialog(new JFrame(), 
                "Consulta marcada como realizada!", 
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(), 
                "Erro ao finalizar consulta: " + ex.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void marcarFalta(Consulta consulta) {
        try {
            Persistencia.deletarConsulta(consulta);

            consulta.setStatus(StatusConsulta.FALTA);

            Persistencia.salvarConsultas(consulta);

            JOptionPane.showMessageDialog(new JFrame(), 
                "Falta registrada!", 
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static boolean horarioDisponivel(Medico medico, LocalDateTime dataHora) {
        try {
            ArrayList<Consulta> consultas = Persistencia.carregarConsultasMedicoAgendadas(medico);
            
            for (Consulta c : consultas) {
                if (c.getDataHora().equals(dataHora)) {
                    return false;
                }
            }
            return true;

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static List<LocalTime> gerarHorariosDisponiveis(Medico medico, LocalDate data) {
        List<LocalTime> horarios = new ArrayList<>();
        
        if (medico.getHorarioInicioExpediente() == null || medico.getHorarioFimExpediente() == null || medico.getDuracaoConsulta() <= 0 || medico.getDiasTrabalha().get(data.getDayOfWeek().getValue()-1) == false) {
            return horarios; 
        }

        try {
            LocalTime inicio = LocalTime.parse(medico.getHorarioInicioExpediente());
            LocalTime fim = LocalTime.parse(medico.getHorarioFimExpediente());           
            int duracaoMinutos = medico.getDuracaoConsulta();

            ArrayList<Consulta> consultasAgendadas = Persistencia.carregarConsultasMedicoAgendadas(medico);
            List<LocalTime> horariosOcupados = new ArrayList<>();
            
            for (Consulta c : consultasAgendadas) {
                if (c.getDataHora().toLocalDate().equals(data)) {
                    horariosOcupados.add(c.getDataHora().toLocalTime());
                }
            }

            LocalTime atual = inicio;
            while (!atual.isAfter(fim.minusMinutes(duracaoMinutos))) {
                LocalDateTime dataHoraCompleta = LocalDateTime.of(data, atual);
                if (dataHoraCompleta.isAfter(LocalDateTime.now())) {
                    if (!horariosOcupados.contains(atual)) {
                        horarios.add(atual);
                    }
                }
                atual = atual.plusMinutes(duracaoMinutos);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return horarios;
    }

    public static ArrayList<Medico> carregarMedicosDisponiveis() {
        ArrayList<Medico> medicosDisponiveis = new ArrayList<>();
        
        try {
            ArrayList<Medico> todosMedicos = Persistencia.carregarMedicos();
            
            for (Medico m : todosMedicos) {
                if (m.getAtividade() == StatusMedico.ATIVO) {
                    medicosDisponiveis.add(m);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return medicosDisponiveis;
    }

/* Implementar automaticamente faltas, se necessário no futuro
    public static void verificarFaltasAutomaticas() {
        try {
            ArrayList<Consulta> todasConsultas = new ArrayList<>();
            
            ArrayList<Paciente> pacientes = Persistencia.carregarPacientes();
            for (Paciente p : pacientes) {
                todasConsultas.addAll(Persistencia.carregarConsultasPacienteAgendadas(p));
            }

            LocalDateTime agora = LocalDateTime.now();
            
            for (Consulta c : todasConsultas) {
                if (c.getDataHora().isBefore(agora) && c.getStatus() == StatusConsulta.AGENDADA) {
                    marcarFalta(c);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } */
}