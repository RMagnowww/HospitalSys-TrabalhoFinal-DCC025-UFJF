package br.ufjf.dcc.view;
import br.ufjf.dcc.model.Consulta;
import javax.swing.*;
import java.awt.*;

public class TelaHistorico{
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelBotao;
    private JList<Consulta> listConsultas;
    private JButton botaoSair;

    public TelaHistorico(){
        frame = new JFrame("Histórico Médico");
        painelPrincipal = new JPanel();
        painelBotao = new JPanel();
        listConsultas = new JList<Consulta>();
        botaoSair = new JButton("Sair");
    }
    public void abrirTelaHistorico(){
        frame.setSize(400,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listConsultas.setBorder(BorderFactory.createTitledBorder("Histórico de Consultas"));
        botaoSair.addActionListener(e -> frame.dispose());
        painelBotao.add(botaoSair);

        painelPrincipal.setLayout(new BorderLayout(10,10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        painelPrincipal.add(new JScrollPane(listConsultas));
        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);

        frame.add(painelPrincipal);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}