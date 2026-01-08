/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */
    
package br.ufjf.dcc.view.TelasSecretario;
import br.ufjf.dcc.controller.SecretarioController;
import br.ufjf.dcc.model.Paciente;
import br.ufjf.dcc.model.Persistencia;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TelaInternadosVisitas{
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelSuperior;
    private JPanel painelInfo;
    private JPanel painelBotoes;
    private JPanel painelList;
    private JPanel painelInfoEsq;
    private JPanel painelInfoDir;
    private JTextField campoPaciente;
    private JList<Paciente> listPacientes;
    private JLabel labelPaciente;
    private JLabel labelAptidao;
    private JLabel campoAptidao;
    private JButton botaoSair;
    private JButton botaoBuscar;
    private ArrayList<Paciente> pacientes;

    public TelaInternadosVisitas(){
        frame = new JFrame("Internações e Visitas");
        painelPrincipal = new JPanel();
        painelSuperior = new JPanel();
        painelInfo = new JPanel();
        painelBotoes = new JPanel();
        painelList = new JPanel();
        painelInfoEsq = new JPanel();
        painelInfoDir = new JPanel();
        campoPaciente = new JTextField();
        listPacientes = new JList<Paciente>();
            try{
                pacientes = Persistencia.carregarPacientes();
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
            listPacientes.setListData(pacientes.toArray(new Paciente[pacientes.size()]));
        labelPaciente = new JLabel("Nome do Paciente:");
        labelAptidao = new JLabel("Aptidão a Visitas:");
        campoAptidao = new JLabel();
        botaoSair = new JButton("Sair");
        botaoBuscar = new JButton("Buscar");
    }
    public void abrirTelaInternadosVisitas(){
        frame.setSize(700,400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(5,5));

        botaoBuscar.addActionListener(e -> {
            String apt = SecretarioController.checarDisponibilidadeVisita(campoPaciente.getText());
            campoAptidao.setText(apt);
            if(apt != null){
                if(apt.equals("APTO"))
                    campoAptidao.setForeground(new Color(0,155,0));
                else
                    campoAptidao.setForeground(new Color(155,35,35));
            }
        });
        botaoSair.addActionListener(e -> frame.dispose());

        listPacientes.addListSelectionListener(e -> {
            campoPaciente.setText(listPacientes.getSelectedValue().getNome());
            String apt = SecretarioController.checarDisponibilidadeVisita(campoPaciente.getText());
            campoAptidao.setText(apt);
            if(apt != null){
                if(apt.equals("APTO"))
                    campoAptidao.setForeground(new Color(0,155,0));
                else
                    campoAptidao.setForeground(new Color(155,35,35));
            }
        });
        painelList.setBorder(BorderFactory.createTitledBorder("Pacientes"));
        painelList.setLayout(new BorderLayout(5,5));
        painelList.add(new JScrollPane(listPacientes));

        painelInfoEsq.setLayout(new GridLayout(2,0,0,15));
        painelInfoEsq.add(labelPaciente);
        painelInfoEsq.add(labelAptidao);

        painelInfoDir.setLayout(new GridLayout(2,0,0,15));
        painelInfoDir.add(campoPaciente);
        painelInfoDir.add(campoAptidao);

        painelInfo.setLayout(new BorderLayout(5,5));
        painelInfo.setBorder(BorderFactory.createEmptyBorder(30,0,220,0));
        painelInfo.add(painelInfoEsq, BorderLayout.WEST);
        painelInfo.add(painelInfoDir, BorderLayout.CENTER);

        painelSuperior.setLayout(new BorderLayout(10,10));
        painelSuperior.add(painelList, BorderLayout.WEST);
        painelSuperior.add(painelInfo, BorderLayout.CENTER);

        painelBotoes.setLayout(new GridLayout(0,2,10,0));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0,75,0,75));
        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoBuscar);

        painelPrincipal.setLayout(new BorderLayout(5,5));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        painelPrincipal.add(painelSuperior, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        frame.add(painelPrincipal);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}