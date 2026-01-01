package br.ufjf.dcc.view.TelasPaciente;
import br.ufjf.dcc.model.Consulta;
import br.ufjf.dcc.model.Paciente;
import br.ufjf.dcc.model.Persistencia;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TelaAgendamento {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelInfo;
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
    private JComboBox<LocalTime> comboBoxHorarios;
    private JTextPane paneDescricao;
    private JLabel respostaStatusConsulta;
    private JList<Consulta> listaAgendamentos;
    private JLabel labelMedico;
    private JLabel labelData;  
    private JLabel labelHorario;
    private JLabel labelStatusConsulta;
    private ArrayList<Consulta> consultas;
    

    public TelaAgendamento(){
        frame = new JFrame("Agendamento de Consultas");
        painelPrincipal = new JPanel();
        painelInfo = new JPanel();
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
        paneDescricao = new JTextPane();
        respostaStatusConsulta = new JLabel();
            respostaStatusConsulta.setForeground(new Color(0,155,0));
        labelMedico = new JLabel("Médico:");
        labelData = new JLabel("Data:");
        labelHorario = new JLabel("Horário:");
        listaAgendamentos = new JList<Consulta>();
        labelStatusConsulta = new JLabel("Status da Consulta:");
    }     

    public void abrirTelaAgendamento(Paciente paciente){
        frame.setSize(800,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(5,5));

        try{
            consultas = Persistencia.carregarConsultasPacienteAgendadas(paciente);
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
        listaAgendamentos.setListData(consultas.toArray(new Consulta[consultas.size()]));
        listaAgendamentos.addListSelectionListener(e -> {
            if(listaAgendamentos.getSelectedValue() != null){
                campoMedico.setText(listaAgendamentos.getSelectedValue().getMedico().getNome());
                campoData.setText(listaAgendamentos.getSelectedValue().getDataHora().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                comboBoxHorarios.setSelectedItem(listaAgendamentos.getSelectedValue().getDataHora().toLocalTime());
                respostaStatusConsulta.setText(listaAgendamentos.getSelectedValue().getStatus().toString());
                paneDescricao.setText(listaAgendamentos.getSelectedValue().getDescricao());
                campoMedico.setEditable(false);
                campoData.setEditable(false);
                comboBoxHorarios.setEditable(false);
                paneDescricao.setEditable(false);
            }
        });

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
        painelBox.add(respostaStatusConsulta);

        painelBotoes.setLayout(new GridLayout(0,3,10,0));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10,20,15,20));
        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoCancelarAgendamento);
        painelBotoes.add(botaoConfirmarAgendamento);

        botaoCancelarAgendamento.addActionListener(e -> {
            if(listaAgendamentos.getSelectedValue() != null){
                try{
                    Persistencia.deletarConsulta(listaAgendamentos.getSelectedValue());
                } 
                catch (IOException ex) {
                    ex.printStackTrace();
                }
                try{
                    consultas = Persistencia.carregarConsultasPacienteAgendadas(paciente);
                } 
                catch (IOException ex) {
                    ex.printStackTrace();
                }
                listaAgendamentos.setListData(consultas.toArray(new Consulta[consultas.size()]));
                campoMedico.setText(null);
                campoData.setText(null);
                comboBoxHorarios.setSelectedItem(null);
                respostaStatusConsulta.setText(null);
                paneDescricao.setText(null);
                campoMedico.setEditable(true);
                campoData.setEditable(true);
                comboBoxHorarios.setEditable(true);
                paneDescricao.setEditable(true);
            }
            else
                JOptionPane.showMessageDialog(new JFrame(),"Não há consulta selecionada para cancelamento!","Erro!", JOptionPane.ERROR_MESSAGE);
        });
        botaoSair.addActionListener(e -> frame.dispose());

        paneDescricao.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder("Descrição")));
        paneDescricao.setPreferredSize(new Dimension(200,150));
        paneDescricao.setMaximumSize(new Dimension(200,150));

        painelInfo.setLayout(new BorderLayout(10,10));
        painelInfo.add(painelLabel, BorderLayout.WEST);
        painelInfo.add(painelBox, BorderLayout.CENTER);
        painelInfo.add(new JScrollPane(paneDescricao), BorderLayout.SOUTH);

        painelAgendar.add(painelInfo, BorderLayout.CENTER);
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
