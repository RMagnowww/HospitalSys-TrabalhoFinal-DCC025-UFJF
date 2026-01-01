package br.ufjf.dcc.view.TelasPaciente;

import br.ufjf.dcc.controller.AgendamentoController;
import br.ufjf.dcc.model.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    private JComboBox<Medico> comboBoxMedicos;
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
    private Paciente pacienteAtual;
    

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
        comboBoxMedicos = new JComboBox<>();
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
        this.pacienteAtual = paciente;

        frame.setSize(800,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(5,5));

        carregarMedicosDisponiveis();

        try{
            consultas = Persistencia.carregarConsultasPacienteAgendadas(paciente);
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
        listaAgendamentos.setListData(consultas.toArray(new Consulta[consultas.size()]));
        listaAgendamentos.addListSelectionListener(e -> {
            if(listaAgendamentos.getSelectedValue() != null){
                Consulta selecionada = listaAgendamentos.getSelectedValue();
                comboBoxMedicos.setSelectedItem(selecionada.getMedico());
                campoData.setText(selecionada.getDataHora().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                comboBoxHorarios.setSelectedItem(selecionada.getDataHora().toLocalTime());
                respostaStatusConsulta.setText(selecionada.getStatus().toString());
                paneDescricao.setText(selecionada.getDescricao());
            }
        });

        comboBoxMedicos.addActionListener(e -> {
            atualizarHorariosDisponiveis();
        });

        campoData.addActionListener(e -> {
            atualizarHorariosDisponiveis();
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
        painelBox.add(comboBoxMedicos);
        painelBox.add(campoData);
        painelBox.add(comboBoxHorarios);
        painelBox.add(respostaStatusConsulta);

        painelBotoes.setLayout(new GridLayout(0,3,10,0));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10,20,15,20));
        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoCancelarAgendamento);
        painelBotoes.add(botaoConfirmarAgendamento);

        botaoConfirmarAgendamento.addActionListener(e -> {
            agendarNovaConsulta();
        });

        botaoCancelarAgendamento.addActionListener(e -> {
            if(listaAgendamentos.getSelectedValue() != null){
                AgendamentoController.cancelarConsulta(listaAgendamentos.getSelectedValue());
                atualizarListaConsultas();
                limparCampos();
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

    private void carregarMedicosDisponiveis() {
        ArrayList<Medico> medicos = AgendamentoController.carregarMedicosDisponiveis();
        comboBoxMedicos.removeAllItems();
        for (Medico m : medicos) {
            comboBoxMedicos.addItem(m);
        }
    }

    private void atualizarHorariosDisponiveis() {
        comboBoxHorarios.removeAllItems();
        
        Medico medicoSelecionado = (Medico) comboBoxMedicos.getSelectedItem();
        String dataTexto = campoData.getText();

        if (medicoSelecionado != null && dataTexto != null && !dataTexto.trim().isEmpty()) {
            try {
                LocalDate data = LocalDate.parse(dataTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                List<LocalTime> horarios = AgendamentoController.gerarHorariosDisponiveis(medicoSelecionado, data);
                
                for (LocalTime horario : horarios) {
                    comboBoxHorarios.addItem(horario);
                }

                if (horarios.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, 
                        "Não há horários disponíveis para esta data!", 
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, 
                    "Data inválida! Use o formato dd/MM/yyyy", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void agendarNovaConsulta() {
        Medico medico = (Medico) comboBoxMedicos.getSelectedItem();
        LocalTime horario = (LocalTime) comboBoxHorarios.getSelectedItem();
        String dataTexto = campoData.getText();
        String descricao = paneDescricao.getText();

        if (medico == null || horario == null || dataTexto == null || dataTexto.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, 
                "Preencha todos os campos obrigatórios!", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            LocalDate data = LocalDate.parse(dataTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDateTime dataHora = LocalDateTime.of(data, horario);

            AgendamentoController.agendarConsulta(pacienteAtual, medico, dataHora, descricao);
            atualizarListaConsultas();
            limparCampos();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, 
                "Erro ao agendar: " + ex.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void atualizarListaConsultas() {
        try {
            consultas = Persistencia.carregarConsultasPacienteAgendadas(pacienteAtual);
            listaAgendamentos.setListData(consultas.toArray(new Consulta[consultas.size()]));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void limparCampos() {
        comboBoxMedicos.setSelectedIndex(0);
        campoData.setText("");
        comboBoxHorarios.removeAllItems();
        paneDescricao.setText("");
        respostaStatusConsulta.setText("");
        listaAgendamentos.clearSelection();
    }
}
