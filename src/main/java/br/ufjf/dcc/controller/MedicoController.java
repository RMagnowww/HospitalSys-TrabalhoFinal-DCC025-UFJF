/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */
    
package br.ufjf.dcc.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.ufjf.dcc.model.Paciente;
import br.ufjf.dcc.model.Persistencia;

public class MedicoController {
    public static String checarDisponibilidadeVisita(String pacienteNome){
        try {
            ArrayList<Paciente> listaPacientes = Persistencia.carregarPacientes();
            for (Paciente p : listaPacientes) {
                if (p.getNome().equals(pacienteNome)) {
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
}
