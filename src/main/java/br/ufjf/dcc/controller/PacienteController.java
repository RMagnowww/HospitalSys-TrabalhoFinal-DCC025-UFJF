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
        try {
            Persistencia.salvarUsuario(p);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(new JFrame(), "Alteração de Dados Pessoais confirmada!", "Successo",JOptionPane.INFORMATION_MESSAGE);
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

