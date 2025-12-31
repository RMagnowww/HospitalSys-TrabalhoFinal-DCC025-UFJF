package br.ufjf.dcc.view.TelasPaciente;
import br.ufjf.dcc.model.Consulta;
import br.ufjf.dcc.model.Paciente;
import br.ufjf.dcc.model.Persistencia;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TelaHistorico{
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelSuperior;
    private JPanel painelBotao;
    private JList<Consulta> listConsultas;
    private JTextPane painelVisualizar;
    private JButton botaoSair;
    private JButton botaoAbrir;
    private ArrayList<Consulta> consultas;

    public TelaHistorico(){
        frame = new JFrame("Histórico Médico");
        painelPrincipal = new JPanel();
        painelSuperior = new JPanel();
        painelBotao = new JPanel();
        listConsultas = new JList<Consulta>();
        painelVisualizar = new JTextPane();
        botaoSair = new JButton("Sair");
        botaoAbrir = new JButton("Abrir");
    }
    public void abrirTelaHistorico(Paciente paciente){
        frame.setSize(600,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listConsultas.setBorder(BorderFactory.createTitledBorder("Histórico de Consultas"));
        try{
            consultas = Persistencia.carregarConsultasPacienteRealizadas(paciente);
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
        listConsultas.setListData(consultas.toArray(new Consulta[consultas.size()]));

        botaoSair.addActionListener(e -> frame.dispose());

        painelBotao.setLayout(new GridLayout(0,2,10,0));
        painelBotao.setBorder(BorderFactory.createEmptyBorder(0,120,0,120));
        painelBotao.add(botaoSair);
        painelBotao.add(botaoAbrir);

        painelVisualizar.setBorder(BorderFactory.createTitledBorder("Visualizador"));
        painelVisualizar.setEditable(false);
        painelVisualizar.setVisible(false);

        painelSuperior.setLayout(new GridLayout(0,2,10,0));
        painelSuperior.add(new JScrollPane(listConsultas));
        painelSuperior.add(painelVisualizar);

        painelPrincipal.setLayout(new BorderLayout(10,10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        painelPrincipal.add(painelSuperior, BorderLayout.CENTER);
        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);

        frame.add(painelPrincipal);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}