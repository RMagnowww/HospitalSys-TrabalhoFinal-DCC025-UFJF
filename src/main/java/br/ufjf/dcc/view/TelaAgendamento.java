package br.ufjf.dcc.view;
import javax.swing.*;
import java.awt.*;

public class TelaAgendamento {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelAgendar;
    private JPanel painelAgendamentos;
    private JPanel painelLabel;
    private JPanel painelBox;
    private JPanel painelBotoes;
    private JButton botaoConfirmarAgendamento;
    private JButton botaoCancelarAgendamento;
    private JButton botaoSair;
    private JTextField campoMedico;
    private JTextField campoData;
    private JComboBox<String> comboBoxHorarios;
    private JLabel statusConsulta;
    private JList<String> listaAgendamentos;
    private JLabel labelMedico;
    private JLabel labelData;  
    private JLabel labelHorario;
    private JLabel labelStatusConsulta;
    

    public TelaAgendamento(){
        frame = new JFrame("Agendamento de Consultas");
        painelPrincipal = new JPanel();
        painelAgendar = new JPanel();  
        painelAgendamentos = new JPanel();
        painelLabel = new JPanel();
        painelBox = new JPanel();
        painelBotoes = new JPanel();
        botaoConfirmarAgendamento = new JButton("Confirmar Agendamento");
        botaoCancelarAgendamento = new JButton("Cancelar Agendamento");
        botaoSair = new JButton("Sair");
        campoMedico = new JTextField();
        campoData = new JTextField();
        comboBoxHorarios = new JComboBox<>();
        statusConsulta = new JLabel();
        labelMedico = new JLabel("Médico:");
        labelData = new JLabel("Data:");
        labelHorario = new JLabel("Horário:");
        listaAgendamentos = new JList<>();
        labelStatusConsulta = new JLabel("Status da Consulta:");
    }

    public void abrirTelaAgendamento(){
        frame.setSize(800,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(5,5));

        painelAgendamentos.setBorder(BorderFactory.createTitledBorder("Consultas Agendadas"));
        painelAgendamentos.setLayout(new BorderLayout(10,10));
        painelAgendamentos.add(new JScrollPane(listaAgendamentos), BorderLayout.CENTER);

        painelAgendar.setBorder(BorderFactory.createTitledBorder("Agendar Consulta"));
        painelAgendar.setLayout(new BorderLayout(10,10));

        painelLabel.setLayout(new GridLayout(4,0,0,20));
        painelLabel.setBorder(BorderFactory.createEmptyBorder(20,20,5,0));
        painelLabel.add(labelMedico);
        painelLabel.add(labelData);
        painelLabel.add(labelHorario);
        painelLabel.add(labelStatusConsulta);

        painelBox.setLayout(new GridLayout(4,0,0,20));
        painelBox.setBorder(BorderFactory.createEmptyBorder(20,0,5,20));
        painelBox.add(campoMedico);
        painelBox.add(campoData);
        painelBox.add(comboBoxHorarios);
        painelBox.add(statusConsulta);

        painelBotoes.setLayout(new GridLayout(0,3,10,0));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(180,20,15,20));
        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoCancelarAgendamento);
        painelBotoes.add(botaoConfirmarAgendamento);

        botaoSair.addActionListener(e -> frame.dispose());

        painelAgendar.add(painelLabel, BorderLayout.WEST);
        painelAgendar.add(painelBox, BorderLayout.CENTER);
        painelAgendar.add(painelBotoes, BorderLayout.SOUTH);

        painelPrincipal.setLayout(new BorderLayout(10,10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        painelPrincipal.add(painelAgendar, BorderLayout.EAST);
        painelPrincipal.add(painelAgendamentos, BorderLayout.CENTER);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
    }


}
