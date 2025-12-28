package br.ufjf.dcc.view;
import br.ufjf.dcc.model.Consulta;
import br.ufjf.dcc.model.enums.StatusInternacao;

import javax.swing.*;
import java.awt.*;

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
    private JComboBox<StatusInternacao> boxStatusInternacao;
    private JButton botaoSair;
    private JButton botaoBuscar;
    private JButton botaoAtualizar;

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
        boxStatusInternacao = new JComboBox<StatusInternacao>();
        botaoSair = new JButton("Sair");
        botaoBuscar = new JButton("Buscar");
        botaoAtualizar = new JButton("Atualizar Status");
    }
    public void abrirHistoricoClinico(){
        frame.setSize(700,400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(5,5));

        botaoSair.addActionListener(e -> frame.dispose());

        painelList.setBorder(BorderFactory.createTitledBorder("Histórico do Paciente"));
        painelList.setLayout(new BorderLayout(5,5));
        painelList.add(new JScrollPane(listConsultas));

        painelInfoEsq.setLayout(new GridLayout(2,0,0,15));
        painelInfoEsq.add(labelPaciente);
        painelInfoEsq.add(labelAptidao);

        painelInfoDir.setLayout(new GridLayout(2,0,0,15));
        painelInfoDir.add(campoPaciente);
        painelInfoDir.add(boxStatusInternacao);

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
