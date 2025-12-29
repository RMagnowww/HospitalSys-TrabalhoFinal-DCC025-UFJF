package br.ufjf.dcc.controller;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import br.ufjf.dcc.model.Endereco;
import br.ufjf.dcc.model.Paciente;
import br.ufjf.dcc.model.Persistencia;

public class SecretarioController{
    public static void cadastrarPaciente(String nome, String cpf, String telefone, String cidade, String bairro, String rua, String num, String cep, String email, String senha, String dataNas, String tipoSang){
        Endereco e = new Endereco(rua, bairro, cep, cidade, num);
        Paciente p = new Paciente(nome, cpf, telefone, email, senha, e, dataNas, tipoSang);
        try {
            Persistencia.salvarUsuario(p);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(new JFrame(),"Usuário Cadastrado!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

}
