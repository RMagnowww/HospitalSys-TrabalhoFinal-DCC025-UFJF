/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */
    
package br.ufjf.dcc.view.TelasMedico;

import br.ufjf.dcc.controller.AgendamentoController;
import br.ufjf.dcc.model.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import br.ufjf.dcc.controller.MedicoController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class TelaAgenda {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JPanel painelInfo;
    private JPanel painelList;
    private JPanel painelBotoes;
    private JPanel painelExpediente;
    private JPanel painelDias;
    private JList<Consulta> listConsultas;
    private JTextField campoInicio;
    private JTextField campoFim;
    private JComboBox<Integer> campoDuracao;
    private JTextField campoConsultaPaciente;
    private JTextField campoConsultaData;
    private JTextField campoConsultaStatus;
    private JTextPane paneDescricao;
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
    private JLabel labelPaciente;
    private JLabel labelData;
    private JLabel labelStatus;
    private JButton botaoSair;
    private JButton botaoSalvar;
    private JButton botaoRealizarConsulta;
    private JButton botaoMarcarFalta;
    
    private Medico medicoAtual;
    private ArrayList<Consulta> consultas;

    public TelaAgenda(){
        frame = new JFrame("Agenda de Atendimentos");
        painelPrincipal = new JPanel();
        painelEsq = new JPanel();
        painelDir = new JPanel();
        painelInfo = new JPanel();
        painelList = new JPanel();
        painelBotoes = new JPanel();
        painelExpediente = new JPanel();
        painelDias = new JPanel();
        listConsultas = new JList<Consulta>();
        campoInicio = new JTextField(10);
        campoFim = new JTextField(10);
        campoDuracao = new JComboBox<Integer>();
            campoDuracao.addItem(15);
            campoDuracao.addItem(30);
            campoDuracao.addItem(45);
            campoDuracao.addItem(60);
        campoConsultaPaciente = new JTextField(20);
        campoConsultaData = new JTextField(20);
        campoConsultaStatus = new JTextField(20);
        paneDescricao = new JTextPane();
        checkSegunda = new JCheckBox("Segunda");
        checkTerca = new JCheckBox("Terça");
        checkQuarta = new JCheckBox("Quarta");
        checkQuinta = new JCheckBox("Quinta");
        checkSexta = new JCheckBox("Sexta");
        checkSabado = new JCheckBox("Sábado");
        checkDomingo = new JCheckBox("Domingo");
        labelInicio = new JLabel("Início (HH:mm):");
        labelFim = new JLabel("Fim (HH:mm):");
        labelDuracao = new JLabel("Duração (mm)");
        labelPaciente = new JLabel("Paciente:");
        labelData = new JLabel("Data/Hora:");
        labelStatus = new JLabel("Status:");
        botaoSair = new JButton("Sair");
        botaoSalvar = new JButton("Salvar Expediente");
        botaoRealizarConsulta = new JButton("Realizar Consulta");
        botaoMarcarFalta = new JButton("Marcar Falta");
        
        campoConsultaPaciente.setEditable(false);
        campoConsultaData.setEditable(false);
        campoConsultaStatus.setEditable(false);
        paneDescricao.setEditable(false);
    }

    public void abrirTelaAgenda(Medico medico){
        this.medicoAtual = medico;
        
        frame.setSize(1030,550);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    
        carregarDadosMedico();
        carregarConsultas();

        listConsultas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && listConsultas.getSelectedValue() != null) {
                exibirDetalhesConsulta();
            }
        });

        botaoSalvar.addActionListener(e -> {
            salvarExpediente();
        });


        botaoRealizarConsulta.addActionListener(e -> {
            realizarConsulta();
        });

        botaoMarcarFalta.addActionListener(e -> {
            marcarFalta();
        });

        botaoSair.addActionListener(e -> frame.dispose());

        configurarPainelExpediente();
        configurarPainelConsultas();
        configurarPainelInfo();

        painelBotoes.setLayout(new GridLayout(0,3,10,0));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0,50,0,50));
        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoRealizarConsulta);
        painelBotoes.add(botaoMarcarFalta);

        painelPrincipal.setLayout(new BorderLayout(10,10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        painelPrincipal.add(painelEsq, BorderLayout.WEST);
        painelPrincipal.add(painelList, BorderLayout.CENTER);
        painelPrincipal.add(painelDir, BorderLayout.EAST);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void configurarPainelExpediente() {
        painelExpediente.setBorder(BorderFactory.createTitledBorder("Horário de Expediente"));
        painelExpediente.setLayout(new GridLayout(4,2,10,10));
        painelExpediente.add(labelInicio);
        painelExpediente.add(campoInicio);
        painelExpediente.add(labelFim);
        painelExpediente.add(campoFim);
        painelExpediente.add(labelDuracao);
        painelExpediente.add(campoDuracao);
        painelExpediente.add(new JLabel());
        painelExpediente.add(botaoSalvar);

        painelDias.setBorder(BorderFactory.createTitledBorder("Dias de Atendimento"));
        painelDias.setLayout(new GridLayout(4,2,5,5));
        painelDias.add(checkSegunda);
        painelDias.add(checkTerca);
        painelDias.add(checkQuarta);
        painelDias.add(checkQuinta);
        painelDias.add(checkSexta);
        painelDias.add(checkSabado);
        painelDias.add(checkDomingo);

        painelEsq.setLayout(new BorderLayout(10,10));
        painelEsq.add(painelExpediente, BorderLayout.NORTH);
        painelEsq.add(painelDias, BorderLayout.CENTER);
    }

    private void configurarPainelConsultas() {
        listConsultas.setBorder(BorderFactory.createTitledBorder("Consultas Agendadas"));
        painelList.setLayout(new BorderLayout(10,10));
        painelList.add(new JScrollPane(listConsultas));
    }

    private void configurarPainelInfo() {
        painelInfo.setBorder(BorderFactory.createTitledBorder("Detalhes da Consulta"));
        painelInfo.setLayout(new GridLayout(3,2,5,10));
        painelInfo.add(labelPaciente);
        painelInfo.add(campoConsultaPaciente);
        painelInfo.add(labelData);
        painelInfo.add(campoConsultaData);
        painelInfo.add(labelStatus);
        painelInfo.add(campoConsultaStatus);

        paneDescricao.setBorder(BorderFactory.createTitledBorder("Observações"));
        paneDescricao.setPreferredSize(new Dimension(250, 200));

        painelDir.setLayout(new BorderLayout(10,10));
        painelDir.add(painelInfo, BorderLayout.NORTH);
        painelDir.add(new JScrollPane(paneDescricao), BorderLayout.CENTER);
    }

    private void carregarDadosMedico() {
        if (medicoAtual.getHorarioInicioExpediente() != null) {
            campoInicio.setText(medicoAtual.getHorarioInicioExpediente());
        }
        if (medicoAtual.getHorarioFimExpediente() != null) {
            campoFim.setText(medicoAtual.getHorarioFimExpediente());
        }
        if (medicoAtual.getDuracaoConsulta() > 0) {
            campoDuracao.setSelectedItem(medicoAtual.getDuracaoConsulta());
        }
        checkSegunda.setSelected(medicoAtual.getDiasTrabalha().get(0));
        checkTerca.setSelected(medicoAtual.getDiasTrabalha().get(1));
        checkQuarta.setSelected(medicoAtual.getDiasTrabalha().get(2));
        checkQuinta.setSelected(medicoAtual.getDiasTrabalha().get(3));
        checkSexta.setSelected(medicoAtual.getDiasTrabalha().get(4));
        checkSabado.setSelected(medicoAtual.getDiasTrabalha().get(5));
        checkDomingo.setSelected(medicoAtual.getDiasTrabalha().get(6));       
    }

    private void carregarConsultas() {
        try {
            consultas = Persistencia.carregarConsultasMedicoAgendadas(medicoAtual);
            listConsultas.setListData(consultas.toArray(new Consulta[0]));
            
            if (!consultas.isEmpty()) {
                int novasConsultas = consultas.size();
                JOptionPane.showMessageDialog(frame, 
                    "Você tem " + novasConsultas + " consulta(s) agendada(s)!", 
                    "Notificação", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void exibirDetalhesConsulta() {
        Consulta consultaSelecionada = listConsultas.getSelectedValue();
        if (consultaSelecionada != null) {
            campoConsultaPaciente.setText(consultaSelecionada.getPaciente().getNome());
            campoConsultaData.setText(consultaSelecionada.getDataHora()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            campoConsultaStatus.setText(consultaSelecionada.getStatus().toString());
            paneDescricao.setText(consultaSelecionada.getDescricao());
        }
    }

    private void salvarExpediente() {
        String inicio = campoInicio.getText();
        String fim = campoFim.getText();
        
        
        int duracao = Integer.parseInt(campoDuracao.getSelectedItem().toString());
        ArrayList<Boolean> diasTrabalha = new ArrayList<Boolean>(7);
            diasTrabalha.add(checkSegunda.isSelected());
            diasTrabalha.add(checkTerca.isSelected());
            diasTrabalha.add(checkQuarta.isSelected());
            diasTrabalha.add(checkQuinta.isSelected());
            diasTrabalha.add(checkSexta.isSelected());
            diasTrabalha.add(checkSabado.isSelected());
            diasTrabalha.add(checkDomingo.isSelected());
        if (inicio == null || inicio.trim().isEmpty() || fim == null || fim.trim().isEmpty() || duracao <= 0) {
            JOptionPane.showMessageDialog(frame, 
                "Preencha os horários de início, fim e a duração!", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        MedicoController.salvarExpediente(medicoAtual,inicio, fim, duracao, diasTrabalha);
    }

    private void realizarConsulta() {
        Consulta consultaSelecionada = listConsultas.getSelectedValue();
        
        if (consultaSelecionada == null) {
            JOptionPane.showMessageDialog(frame, 
                "Selecione uma consulta!", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String observacoes = JOptionPane.showInputDialog(frame, 
            "Digite as observações médicas da consulta:", 
            "Observações", JOptionPane.PLAIN_MESSAGE);

        if (observacoes != null) {
            AgendamentoController.realizarConsulta(consultaSelecionada, observacoes);
            carregarConsultas();
            limparDetalhes();
        }
    }

    private void marcarFalta() {
        Consulta consultaSelecionada = listConsultas.getSelectedValue();
        
        if (consultaSelecionada == null) {
            JOptionPane.showMessageDialog(frame, 
                "Selecione uma consulta!", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirma = JOptionPane.showConfirmDialog(frame, 
            "Confirma que o paciente faltou à consulta?", 
            "Confirmar Falta", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {
            AgendamentoController.marcarFalta(consultaSelecionada);
            carregarConsultas();
            limparDetalhes();
        }
    }
    
    private void limparDetalhes() {
        campoConsultaPaciente.setText("");
        campoConsultaData.setText("");
        campoConsultaStatus.setText("");
        paneDescricao.setText("");
        listConsultas.clearSelection();
    }
}