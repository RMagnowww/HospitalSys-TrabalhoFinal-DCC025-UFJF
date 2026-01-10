/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */
    
package br.ufjf.dcc.controller;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.util.ArrayList;

import br.ufjf.dcc.model.enums.StatusMedico;
import br.ufjf.dcc.model.Usuario;
import br.ufjf.dcc.model.Paciente;
import br.ufjf.dcc.model.Endereco;
import br.ufjf.dcc.model.Medico;
import br.ufjf.dcc.model.Persistencia;

public class SecretarioController{

    public static void deletarUsuario(Usuario u){
         try {
            Persistencia.deletarUsuario(u);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if(u instanceof Paciente)
            JOptionPane.showMessageDialog(new JFrame(),"Paciente Removido!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
        if(u instanceof Medico)
            JOptionPane.showMessageDialog(new JFrame(),"Médico Removido!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void cadastrarPaciente(String nome, String cpf, String telefone, String cidade, String bairro, String rua, String num, String cep, String email, String senha, String dataNas, String tipoSang){
        try{
        Endereco e = new Endereco(rua, bairro, cep, cidade, num);
        Paciente p = new Paciente(nome, cpf, telefone, email, senha, e, dataNas, tipoSang);
        Persistencia.salvarUsuario(p);
        JOptionPane.showMessageDialog(new JFrame(),"Paciente Cadastrado!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null,
                ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE
            );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void alterarDadosPaciente(Paciente p,String nome, String cpf, String telefone, String cidade, String bairro, String rua, String num, String cep, String email, String senha, String dataNas, String tipoSang){
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

    public static void cadastrarMedico(String nome, String cpf, String telefone, String email, String senha, String crm, String especialidade, String atividade){
        try {
        Medico m = new Medico(nome, cpf, telefone, email, senha, crm, especialidade);
        if(atividade == null || atividade.equals("ATIVO"))
            m.setAtividade(StatusMedico.ATIVO);
        else if(atividade.equals("INATIVO"))
                m.setAtividade(StatusMedico.INATIVO);
        Persistencia.salvarUsuario(m);
        JOptionPane.showMessageDialog(new JFrame(),"Médico Cadastrado!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null,
                ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE
            );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void alterarDadosMedico(Medico m,String nome, String cpf, String telefone, String crm, String especialidade, String email, String senha, String atividade){
        try {
            Persistencia.deletarUsuario(m);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try{
        m.setNome(nome);
        m.setCpf(cpf);
        m.setTelefone(telefone);
        m.setCrm(crm);
        m.setEspecialidade(especialidade);
        m.setEmail(email);
        m.setSenha(senha);
        if(atividade.equals("ATIVO"))
            m.setAtividade(StatusMedico.ATIVO);
        else if(atividade.equals("INATIVO"))
                m.setAtividade(StatusMedico.INATIVO);
        
        JOptionPane.showMessageDialog(new JFrame(), "Alteração de Dados Médico Confirmada!", "Successo",JOptionPane.INFORMATION_MESSAGE);
        } catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null,
                ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            
        } 
        try {
            Persistencia.salvarUsuario(m);
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
