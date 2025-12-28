package br.ufjf.dcc.view.TelasMedico;
import br.ufjf.dcc.model.Consulta;
import javax.swing.*;
import java.awt.*;

public class TelaAgenda {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JPanel painelInfo;
    private JPanel painelList;
    private JPanel painelBotoes;
    private JList<Consulta> listConsultas;
    private JTextField campoInicio;
    private JTextField campoFim;
    private JTextField campoDuracao;
    private JCheckBox checkSegunda;
    private JCheckBox checkTerca;
    private JCheckBox checkQuarta;
    private JCheckBox checkQuinta;
    private JCheckBox checkSexta;
    private JCheckBox checkSabado;
    private JCheckBox checkDomingo;
    private JLabel labelInicio;
    private JLabel labelFim;
    private JLabel labelDuracao;
    private JLabel labelDiasTrab;
    private JButton botaoSair;
    private JButton botaoSalvar;

    

    public TelaAgenda(){
        frame = new JFrame("Agenda de Atendimentos");
        painelPrincipal = new JPanel();
        painelEsq = new JPanel();
        painelDir = new JPanel();
        painelInfo = new JPanel();
        painelList = new JPanel();
        painelBotoes = new JPanel();
        listConsultas = new JList<Consulta>();
        campoInicio = new JTextField();
        campoFim = new JTextField();
        campoDuracao = new JTextField();
        checkSegunda = new JCheckBox("Segunda");
        checkTerca = new JCheckBox("Terça");
        checkQuarta = new JCheckBox("Quarta");
        checkQuinta = new JCheckBox("Quinta");
        checkSexta = new JCheckBox("Sexta");
        checkSabado = new JCheckBox("Sabado");
        checkDomingo = new JCheckBox("Domingo");
        labelInicio = new JLabel("Horário de Início do Expediente:");
        labelFim = new JLabel("Horário de Fim do Expediente:");
        labelDuracao = new JLabel("Duração do Atendimento (minutos):");
        labelDiasTrab = new JLabel("Selecione os dias de Trabalho:");
        botaoSair = new JButton("Sair");
        botaoSalvar = new JButton("Salvar");
        
    }

    public void abrirTelaAgenda(){
        frame.setSize(750,350);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        botaoSair.addActionListener(e -> frame.dispose());

        painelBotoes.setLayout(new GridLayout(0,2,10,0));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0,75,0,325));
        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoSalvar);


        listConsultas.setBorder(BorderFactory.createTitledBorder("Consultas Agendadas"));

        painelList.setLayout(new BorderLayout(10,10));
        painelList.add(new JScrollPane(listConsultas));

        painelEsq.setLayout(new GridLayout(7,0,0,10));
        painelEsq.add(labelInicio);
        painelEsq.add(labelFim);
        painelEsq.add(labelDuracao);
        painelEsq.add(labelDiasTrab);
        painelEsq.add(checkTerca);
        painelEsq.add(checkQuarta);
        painelEsq.add(checkQuinta);

        painelDir.setLayout(new GridLayout(7,0,0,10));
        painelDir.add(campoInicio);
        painelDir.add(campoFim);
        painelDir.add(campoDuracao);
        painelDir.add(checkSegunda);
        painelDir.add(checkSexta);
        painelDir.add(checkSabado);
        painelDir.add(checkDomingo);

        painelInfo.setLayout(new GridLayout(0,2,10,0));
        painelInfo.add(painelEsq);
        painelInfo.add(painelDir);

        painelPrincipal.setLayout(new BorderLayout(10,10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        painelPrincipal.add(painelInfo, BorderLayout.CENTER);
        painelPrincipal.add(painelList, BorderLayout.EAST);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
