package br.ufjf.dcc.view;
import java.time.LocalDateTime;
import br.ufjf.dcc.model.Medico;
import br.ufjf.dcc.model.Consulta;

import javax.swing.*;
import java.awt.*;

public class TelaDisponibilidadeMedicos{
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelSuperior;
    private JPanel painelLabels1;
    private JPanel painelLabels2;
    private JPanel painelBox1;
    private JPanel painelBox2;
    private JPanel painelInfo1;
    private JPanel painelInfo2;
    private JPanel painelInfoMain;
    private JPanel painelLists;
    private JPanel painelBotoes;
    private JList<Medico> listMedicosAtivos;
    private JList<Consulta> listConsultaFalta;
    private JTextField campoMedico1;
    private JTextField campoMedico2;
    private JTextField campoPaciente;
    private JTextField campoHorario;
    private JComboBox<LocalDateTime> boxHorarios;
    private JLabel labelMedico1;
    private JLabel labelMedico2;
    private JLabel labelPaciente;
    private JLabel labelHorarioConsulta;
    private JLabel labelHorariosDisponiveis;
    private JButton botaoSair;

    public TelaDisponibilidadeMedicos(){
        frame = new JFrame("Disponibilidade");
        painelPrincipal = new JPanel();
        painelSuperior = new JPanel();
        painelLabels1 = new JPanel();
        painelLabels2 = new JPanel();
        painelBox1 = new JPanel();
        painelBox2 = new JPanel();
        painelInfo1 = new JPanel();
        painelInfo2 = new JPanel();
        painelInfoMain = new JPanel();
        painelLists = new JPanel();
        painelBotoes = new JPanel();
        listMedicosAtivos = new JList<Medico>();
        listConsultaFalta = new JList<Consulta>();
        campoMedico1 = new JTextField();
        campoMedico2 = new JTextField();
        campoPaciente = new JTextField();
        campoHorario = new JTextField();
        campoMedico1.setEditable(false);
        campoMedico2.setEditable(false);
        campoPaciente.setEditable(false);
        campoHorario.setEditable(false);
        boxHorarios = new JComboBox<LocalDateTime>();
        labelMedico1 = new JLabel("Médico:");
        labelMedico2 = new JLabel("Médico:");
        labelPaciente = new JLabel("Paciente:");
        labelHorarioConsulta = new JLabel("Horário:");
        labelHorariosDisponiveis = new JLabel("Horários Disponíveis:");
        botaoSair = new JButton("Sair");
    }

    public void abrirDispoibilidadeMedicos(){
        frame.setSize(600,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(10,10));

        botaoSair.addActionListener(e -> frame.dispose());

        listMedicosAtivos.setBorder(BorderFactory.createTitledBorder("Medicos Disponíveis"));
        listConsultaFalta.setBorder(BorderFactory.createTitledBorder("Consultas com falta"));

        painelLists.setLayout(new GridLayout(2,0,0,15));
        painelLists.add(new JScrollPane(listMedicosAtivos));
        painelLists.add(new JScrollPane(listConsultaFalta));

        painelLabels1.setLayout(new GridLayout(2,0,0,10));
        painelLabels1.add(labelMedico1);
        painelLabels1.add(labelHorariosDisponiveis);

        painelLabels2.setLayout(new GridLayout(3,0,0,10));
        painelLabels2.add(labelMedico2);
        painelLabels2.add(labelPaciente);
        painelLabels2.add(labelHorarioConsulta);

        painelBox1.setLayout(new GridLayout(2,0,0,10));
        painelBox1.add(campoMedico1);
        painelBox1.add(boxHorarios);

        painelBox2.setLayout(new GridLayout(3,0,0,10));
        painelBox2.add(campoMedico2);
        painelBox2.add(campoPaciente);
        painelBox2.add(campoHorario);

        painelInfo1.setLayout(new BorderLayout(10,10));
        painelInfo1.setBorder(BorderFactory.createEmptyBorder(0,0,135,0));
        painelInfo1.add(painelLabels1, BorderLayout.WEST);
        painelInfo1.add(painelBox1, BorderLayout.CENTER);

        painelInfo2.setLayout(new BorderLayout(10,10));
        painelInfo2.setBorder(BorderFactory.createEmptyBorder(0,0,100,0));
        painelInfo2.add(painelLabels2, BorderLayout.WEST);
        painelInfo2.add(painelBox2, BorderLayout.CENTER);

        painelInfoMain.setLayout(new GridLayout(2,0,0,10));
        painelInfoMain.add(painelInfo1);
        painelInfoMain.add(painelInfo2);

        painelSuperior.setLayout(new BorderLayout(10,10));
        painelSuperior.add(painelLists, BorderLayout.WEST);
        painelSuperior.add(painelInfoMain, BorderLayout.CENTER);

        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0,175,0,175));
        painelBotoes.setLayout(new BorderLayout(10,10));
        painelBotoes.add(botaoSair);

        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        painelPrincipal.setLayout(new BorderLayout(10,10));
        painelPrincipal.add(painelSuperior);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
