package br.ufjf.dcc.view.TelasPaciente;
import br.ufjf.dcc.model.Consulta;
import br.ufjf.dcc.model.Paciente;
import br.ufjf.dcc.model.Persistencia;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class TelaHistorico{
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelSuperior;
    private JPanel painelInfo;
    private JPanel painelInfoLabel;
    private JPanel painelInfoText;
    private JPanel painelBotao;
    private JList<Consulta> listConsultas;
    private JButton botaoSair;
    private JTextField campoPaciente;
    private JTextField campoMedico;
    private JTextField campoEspecialidade;
    private JTextField campoDataHora;
    private JTextPane paneDescricao;
    private JLabel labelPaciente;
    private JLabel labelMedico;
    private JLabel labelEspecialidade;
    private JLabel labelDataHora;
    private ArrayList<Consulta> consultas;

    public TelaHistorico(){
        frame = new JFrame("Histórico Médico");
        painelPrincipal = new JPanel();
        painelSuperior = new JPanel();
        painelInfo = new JPanel();
        painelInfoLabel = new JPanel();
        painelInfoText = new JPanel();
        campoPaciente = new JTextField(25);
        campoMedico = new JTextField(25);
        campoEspecialidade = new JTextField(25);
        campoDataHora = new JTextField(25);
        paneDescricao = new JTextPane();
        labelPaciente = new JLabel("Paciente:");
        labelMedico= new JLabel("Medico:");
        labelEspecialidade = new JLabel("Especialidade:");
        labelDataHora = new JLabel("Data/Hora:");
        painelBotao = new JPanel();
        listConsultas = new JList<Consulta>();
        botaoSair = new JButton("Sair");
        campoPaciente.setEditable(false);
        campoMedico.setEditable(false);
        campoEspecialidade.setEditable(false);
        campoDataHora.setEditable(false);
        paneDescricao.setEditable(false);
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
        listConsultas.addListSelectionListener(e -> {
            campoPaciente.setText(listConsultas.getSelectedValue().getPaciente().getNome());
            campoMedico.setText(listConsultas.getSelectedValue().getMedico().getNome());
            campoEspecialidade.setText(listConsultas.getSelectedValue().getMedico().getEspecialidade());
            campoDataHora.setText(listConsultas.getSelectedValue().getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            paneDescricao.setText(listConsultas.getSelectedValue().getDescricao());
        });

        botaoSair.addActionListener(e -> frame.dispose());

        painelBotao.setLayout(new GridLayout(0,1,10,0));
        painelBotao.setBorder(BorderFactory.createEmptyBorder(0,150,0,150));
        painelBotao.add(botaoSair);

        painelInfoLabel.setLayout(new GridLayout(4,0,0,10));
        painelInfoLabel.add(labelPaciente);
        painelInfoLabel.add(labelMedico);
        painelInfoLabel.add(labelEspecialidade);
        painelInfoLabel.add(labelDataHora);

        painelInfoText.setLayout(new GridLayout(4,0,0,10));
        painelInfoText.add(campoPaciente);
        painelInfoText.add(campoMedico);
        painelInfoText.add(campoEspecialidade);
        painelInfoText.add(campoDataHora);

        paneDescricao.setBorder(BorderFactory.createTitledBorder("Descrição"));
        paneDescricao.setPreferredSize(new Dimension(200,200));
        paneDescricao.setMaximumSize(new Dimension(200,200));
        painelInfo.setLayout(new BorderLayout(10,10));
        painelInfo.add(painelInfoLabel, BorderLayout.WEST);
        painelInfo.add(painelInfoText, BorderLayout.CENTER);
        painelInfo.add(new JScrollPane(paneDescricao), BorderLayout.SOUTH);

        painelSuperior.setLayout(new BorderLayout(10,10));
        painelSuperior.add(new JScrollPane(listConsultas), BorderLayout.CENTER);
        painelSuperior.add(painelInfo, BorderLayout.EAST);

        painelPrincipal.setLayout(new BorderLayout(10,10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        painelPrincipal.add(painelSuperior, BorderLayout.CENTER);
        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);

        frame.add(painelPrincipal);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}