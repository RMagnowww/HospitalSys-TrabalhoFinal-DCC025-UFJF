/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */
    
package br.ufjf.dcc.controller;

import java.io.IOException;
import java.util.ArrayList;

import br.ufjf.dcc.model.Medico;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.ufjf.dcc.model.Paciente;
import br.ufjf.dcc.model.Persistencia;

public class MedicoController {
    public static String checarDisponibilidadeVisita(String pacienteNome, String pacienteCpf){
        pacienteCpf = pacienteCpf.replaceAll("[^0-9]", "");
        if(pacienteCpf.length() == 11){
            pacienteCpf = String.format("%s.%s.%s-%s", 
            pacienteCpf.substring(0, 3),   
            pacienteCpf.substring(3, 6),  
            pacienteCpf.substring(6, 9),  
            pacienteCpf.substring(9));
            try {
                ArrayList<Paciente> listaPacientes = Persistencia.carregarPacientes();
                for (Paciente p : listaPacientes) {
                    if (p.getNome().equals(pacienteNome) && p.getCpf().equals(pacienteCpf)) {
                        if(p.isAceitaVisitas())
                            return "APTO";
                        else if(!p.isAceitaVisitas())
                            return "NÃO APTO";
                    }
                }

            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        JOptionPane.showMessageDialog(new JFrame(),"Paciente não encontrado!","ERRO", JOptionPane.ERROR_MESSAGE);
        return null;
    }
    public static void atualizarAptidao(String pacienteNome, String aptidaoNova){
            try {
                ArrayList<Paciente> listaPacientes = Persistencia.carregarPacientes();
                for (Paciente p : listaPacientes) {
                    if (p.getNome().equals(pacienteNome)) {
                        if(p.isAceitaVisitas() && aptidaoNova.equals("NÃO APTO")){
                            Persistencia.deletarUsuario(p);
                            p.setAceitaVisitas(false);
                            Persistencia.salvarUsuario(p);
                        }
                        else if(!p.isAceitaVisitas() && aptidaoNova.equals("APTO")){
                                Persistencia.deletarUsuario(p);
                                p.setAceitaVisitas(true);
                                Persistencia.salvarUsuario(p);
                        }  
                    }
                }

            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
    }

    public static void salvarExpediente(Medico medicoAtual,String inicio, String fim, int duracao, ArrayList<Boolean> diasTrabalha){
        //if para verificar formatacao de inicio e fim em HH:mm
        if (!inicio.matches("\\d{2}:\\d{2}") || !fim.matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(new JFrame(),
                "Horários de início e fim de expediente devem estar no formato HH:mm.",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //if para verificar se fim > inicio
        String[] partesInicio = inicio.split(":");
        String[] partesFim = fim.split(":");
        int horaInicio = Integer.parseInt(partesInicio[0]);
        int minutoInicio = Integer.parseInt(partesInicio[1]);
        int horaFim = Integer.parseInt(partesFim[0]);
        int minutoFim = Integer.parseInt(partesFim[1]);
        if (horaFim < horaInicio || (horaFim == horaInicio && minutoFim <= minutoInicio)) {
            JOptionPane.showMessageDialog(new JFrame(),
                "Horário de fim de expediente deve ser maior que o horário de início.",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFrame frame = new JFrame();
        try {
            Persistencia.deletarUsuario(medicoAtual);
            } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, 
                "Erro ao salvar expediente: " + ex.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }

        try{
            medicoAtual.setHorarioInicioExpediente(inicio);
            medicoAtual.setHorarioFimExpediente(fim);
            medicoAtual.setDuracaoConsulta(duracao);
            medicoAtual.setDiasTrabalha(diasTrabalha);
            JOptionPane.showMessageDialog(frame, 
                "Expediente salvo com sucesso!", 
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null,
                ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } 
        try{
            Persistencia.salvarUsuario(medicoAtual);
        }catch(IOException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, 
                "Erro ao salvar expediente: " + ex.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
