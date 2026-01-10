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

public class PacienteController {
    public static void alterarDados(Paciente p,String nome, String cpf, String telefone, String cidade, String bairro, String rua, String num, String cep, String email, String senha, String dataNas, String tipoSang){
         try {
            Persistencia.deletarUsuario(p);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try{
            p.setNome(nome);
            p.setCpf(cpf);
            p.setTelefone(telefone);
            p.getEndereco().setCidade(cidade);
            p.getEndereco().setBairro(bairro);
            p.getEndereco().setRua(rua);
            p.getEndereco().setNumero(num);
            p.getEndereco().setCep(cep);
            p.setEmail(email);
            p.setSenha(senha);
            p.setDataNascimento(dataNas);
            p.setTipoSanguineo(tipoSang);

            JOptionPane.showMessageDialog(new JFrame(), "Alteração de Dados de Paciente Confirmada!", "Successo",JOptionPane.INFORMATION_MESSAGE);
            
        } catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null,
                ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            
        }
        try {
            Persistencia.salvarUsuario(p);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
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
}

