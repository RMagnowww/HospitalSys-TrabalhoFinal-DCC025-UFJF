package br.ufjf.dcc.view.TelasSecretario;
import br.ufjf.dcc.model.Paciente;
import javax.swing.*;
import java.awt.*;

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
    private JList<Paciente> listInternados;
    private JLabel labelPaciente;
    private JLabel labelAptidao;
    private JLabel labelStatusInternacao;
    private JButton botaoSair;
    private JButton botaoBuscar;
    //private StatusInternacao statusPaciente;

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
        listInternados = new JList<Paciente>();
        labelPaciente = new JLabel("Nome do Paciente:");
        labelAptidao = new JLabel("Aptidão a Visitas:");
        labelStatusInternacao = new JLabel();
        botaoSair = new JButton("Sair");
        botaoBuscar = new JButton("Buscar");
    }
    public void abrirTelaInternadosVisitas(){
        frame.setSize(700,400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(5,5));

        botaoSair.addActionListener(e -> frame.dispose());

        painelList.setBorder(BorderFactory.createTitledBorder("Pacientes Internados"));
        painelList.setLayout(new BorderLayout(5,5));
        painelList.add(new JScrollPane(listInternados));

        painelInfoEsq.setLayout(new GridLayout(2,0,0,15));
        painelInfoEsq.add(labelPaciente);
        painelInfoEsq.add(labelAptidao);

        painelInfoDir.setLayout(new GridLayout(2,0,0,15));
        painelInfoDir.add(campoPaciente);
        painelInfoDir.add(labelStatusInternacao);

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