package br.ufjf.dcc.view.TelasMedico;
import br.ufjf.dcc.controller.MedicoController;
import br.ufjf.dcc.model.Paciente;
import br.ufjf.dcc.model.Persistencia;
import br.ufjf.dcc.model.Consulta;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TelaHistoricoClinico {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelSuperior;
    private JPanel painelInfo;
    private JPanel painelBotoes;
    private JPanel painelList;
    private JPanel painelInfoEsq;
    private JPanel painelInfoDir;
    private JTextField campoPaciente;
    private JList<Consulta> listConsultas;
    private JLabel labelPaciente;
    private JLabel labelAptidao;
    private JComboBox<String> boxAptidao;
    private JButton botaoSair;
    private JButton botaoBuscar;
    private JButton botaoAtualizar;
    private ArrayList<Consulta> consultas;

    public TelaHistoricoClinico(){
        frame = new JFrame("Histórico Clínico");
        painelPrincipal = new JPanel();
        painelSuperior = new JPanel();
        painelInfo = new JPanel();
        painelBotoes = new JPanel();
        painelList = new JPanel();
        painelInfoEsq = new JPanel();
        painelInfoDir = new JPanel();
        campoPaciente = new JTextField();
        listConsultas = new JList<Consulta>();
        labelPaciente = new JLabel("Paciente:");
        labelAptidao = new JLabel("Status:");
        boxAptidao = new JComboBox<String>();
            boxAptidao.addItem("APTO");
            boxAptidao.addItem("NÃO APTO");
            boxAptidao.setSelectedItem(null);
        botaoSair = new JButton("Sair");
        botaoBuscar = new JButton("Buscar");
        botaoAtualizar = new JButton("Atualizar Status");
    }
    public void abrirTelaHistoricoClinico(){
        frame.setSize(700,400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(5,5));

        botaoBuscar.addActionListener( e -> {
           String apt = MedicoController.checarDisponibilidadeVisita(campoPaciente.getText());
           if(apt != null){
                if(apt.equals("APTO"))
                    boxAptidao.setSelectedIndex(0);
                else if(apt.equals("NÃO APTO"))
                        boxAptidao.setSelectedIndex(1);
                try{
                    ArrayList<Paciente> listaPacientes = Persistencia.carregarPacientes();
                    for (Paciente p : listaPacientes) {
                        if (p.getNome().equals(campoPaciente.getText())){
                            try{
                                consultas = Persistencia.carregarConsultasPacienteRealizadas(p);
                            } 
                            catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            listConsultas.setListData(consultas.toArray(new Consulta[consultas.size()]));
                        }
                    }
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        botaoAtualizar.addActionListener(e -> {
            MedicoController.atualizarAptidao(campoPaciente.getText(), boxAptidao.getSelectedItem().toString());
            String apt = MedicoController.checarDisponibilidadeVisita(campoPaciente.getText());
            if(apt != null)
                if(apt.equals("APTO"))
                    boxAptidao.setSelectedIndex(0);
                else if(apt.equals("NÃO APTO"))
                        boxAptidao.setSelectedIndex(1);

        });
        botaoSair.addActionListener(e -> frame.dispose());

        painelList.setBorder(BorderFactory.createTitledBorder("Histórico do Paciente"));
        painelList.setLayout(new BorderLayout(5,5));
        painelList.add(new JScrollPane(listConsultas));

        painelInfoEsq.setLayout(new GridLayout(2,0,0,15));
        painelInfoEsq.add(labelPaciente);
        painelInfoEsq.add(labelAptidao);

        painelInfoDir.setLayout(new GridLayout(2,0,0,15));
        painelInfoDir.add(campoPaciente);
        painelInfoDir.add(boxAptidao);

        painelInfo.setLayout(new BorderLayout(5,5));
        painelInfo.setBorder(BorderFactory.createEmptyBorder(30,0,220,0));
        painelInfo.add(painelInfoEsq, BorderLayout.WEST);
        painelInfo.add(painelInfoDir, BorderLayout.CENTER);

        painelSuperior.setLayout(new BorderLayout(10,10));
        painelSuperior.add(painelInfo, BorderLayout.CENTER);
        painelSuperior.add(painelList, BorderLayout.EAST);

        painelBotoes.setLayout(new GridLayout(0,3,5,0));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0,5,0,270));
        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoAtualizar);
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
