package br.ufjf.dcc.view.TelasMedico;
import br.ufjf.dcc.controller.MedicoController;
import br.ufjf.dcc.model.Paciente;
import br.ufjf.dcc.model.Persistencia;
import br.ufjf.dcc.model.Consulta;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class TelaHistoricoClinico {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelSuperior;
    private JPanel painelBusca;
    private JPanel painelInfo;
    private JPanel painelInfoLabel;
    private JPanel painelInfoText;
    private JPanel painelBotoes;
    private JPanel painelList;
    private JPanel painelBuscaEsq;
    private JPanel painelBuscaDir;
    private JTextField campoPacienteBusca;
    private JList<Consulta> listConsultas;
    private JLabel labelPacienteBusca;
    private JLabel labelAptidao;
    private JTextField campoPaciente;
    private JTextField campoMedico;
    private JTextField campoEspecialidade;
    private JTextField campoDataHora;
    private JTextPane paneDescricao;
    private JLabel labelPaciente;
    private JLabel labelMedico;
    private JLabel labelEspecialidade;
    private JLabel labelDataHora;
    private JComboBox<String> boxAptidao;
    private JButton botaoSair;
    private JButton botaoBuscar;
    private JButton botaoAtualizar;
    private ArrayList<Consulta> consultas;

    public TelaHistoricoClinico(){
        frame = new JFrame("Histórico Clínico");
        painelPrincipal = new JPanel();
        painelSuperior = new JPanel();
        painelBusca = new JPanel();
        painelInfo = new JPanel();
        painelInfoLabel = new JPanel();
        painelInfoText = new JPanel();
        painelBotoes = new JPanel();
        painelList = new JPanel();
        painelBuscaEsq = new JPanel();
        painelBuscaDir = new JPanel();
        campoPacienteBusca = new JTextField(20);
        listConsultas = new JList<Consulta>();
        labelPacienteBusca = new JLabel("Paciente:");
        labelAptidao = new JLabel("Status:");
        campoPaciente = new JTextField(25);
        campoMedico = new JTextField(25);
        campoEspecialidade = new JTextField(25);
        campoDataHora = new JTextField(25);
        paneDescricao = new JTextPane();
        labelPaciente = new JLabel("Paciente:");
        labelMedico= new JLabel("Medico:");
        labelEspecialidade = new JLabel("Especialidade:");
        labelDataHora = new JLabel("Data/Hora:");
        boxAptidao = new JComboBox<String>();
            boxAptidao.addItem("APTO");
            boxAptidao.addItem("NÃO APTO");
            boxAptidao.setSelectedItem(null);
        botaoSair = new JButton("Sair");
        botaoBuscar = new JButton("Buscar");
        botaoAtualizar = new JButton("Atualizar Status");
        campoPaciente.setEditable(false);
        campoMedico.setEditable(false);
        campoEspecialidade.setEditable(false);
        campoDataHora.setEditable(false);
        paneDescricao.setEditable(false);
    }
    public void abrirTelaHistoricoClinico(){
        frame.setSize(1000,450);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(5,5));

        botaoBuscar.addActionListener( e -> {
           String apt = MedicoController.checarDisponibilidadeVisita(campoPacienteBusca.getText());
           if(apt != null){
                if(apt.equals("APTO"))
                    boxAptidao.setSelectedIndex(0);
                else if(apt.equals("NÃO APTO"))
                        boxAptidao.setSelectedIndex(1);
                try{
                    ArrayList<Paciente> listaPacientes = Persistencia.carregarPacientes();
                    for (Paciente p : listaPacientes) {
                        if (p.getNome().equals(campoPacienteBusca.getText())){
                            try{
                                consultas = Persistencia.carregarConsultasPacienteRealizadas(p);
                            } 
                            catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            listConsultas.setListData(consultas.toArray(new Consulta[consultas.size()]));
                        }
                    }
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        botaoAtualizar.addActionListener(e -> {
            MedicoController.atualizarAptidao(campoPacienteBusca.getText(), boxAptidao.getSelectedItem().toString());
            String apt = MedicoController.checarDisponibilidadeVisita(campoPacienteBusca.getText());
            if(apt != null)
                if(apt.equals("APTO"))
                    boxAptidao.setSelectedIndex(0);
                else if(apt.equals("NÃO APTO"))
                        boxAptidao.setSelectedIndex(1);

        });
        botaoSair.addActionListener(e -> frame.dispose());

        listConsultas.addListSelectionListener(e -> {
            if(listConsultas.getSelectedValue() != null){
                campoPaciente.setText(listConsultas.getSelectedValue().getPaciente().getNome());
                campoMedico.setText(listConsultas.getSelectedValue().getMedico().getNome());
                campoEspecialidade.setText(listConsultas.getSelectedValue().getMedico().getEspecialidade());
                campoDataHora.setText(listConsultas.getSelectedValue().getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                paneDescricao.setText(listConsultas.getSelectedValue().getDescricao());
            }
        });

        painelList.setBorder(BorderFactory.createTitledBorder("Histórico do Paciente"));
        painelList.setLayout(new BorderLayout(5,5));
        painelList.add(new JScrollPane(listConsultas));

        painelBuscaEsq.setLayout(new GridLayout(2,0,0,15));
        painelBuscaEsq.add(labelPacienteBusca);
        painelBuscaEsq.add(labelAptidao);

        painelBuscaDir.setLayout(new GridLayout(2,0,0,15));
        painelBuscaDir.add(campoPacienteBusca);
        painelBuscaDir.add(boxAptidao);

        painelBusca.setLayout(new BorderLayout(5,5));
        painelBusca.setBorder(BorderFactory.createEmptyBorder(30,0,250,0));
        painelBusca.add(painelBuscaEsq, BorderLayout.WEST);
        painelBusca.add(painelBuscaDir, BorderLayout.CENTER);

        painelSuperior.setLayout(new BorderLayout(10,10));
        painelSuperior.add(painelBusca, BorderLayout.WEST);
        painelSuperior.add(painelList, BorderLayout.CENTER);
        painelSuperior.add(painelInfo, BorderLayout.EAST);

        painelBotoes.setLayout(new GridLayout(0,3,5,0));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0,5,0,400));
        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoAtualizar);
        painelBotoes.add(botaoBuscar);

        painelInfoLabel.setLayout(new GridLayout(4,0,0,10));
        painelInfoLabel.setBorder(BorderFactory.createEmptyBorder(10,10,0,0));
        painelInfoLabel.add(labelPaciente);
        painelInfoLabel.add(labelMedico);
        painelInfoLabel.add(labelEspecialidade);
        painelInfoLabel.add(labelDataHora);

        painelInfoText.setLayout(new GridLayout(4,0,0,10));
        painelInfoText.add(campoPaciente);
        painelInfoText.setBorder(BorderFactory.createEmptyBorder(10,0,0,10));
        painelInfoText.add(campoMedico);
        painelInfoText.add(campoEspecialidade);
        painelInfoText.add(campoDataHora);

        paneDescricao.setBorder(BorderFactory.createTitledBorder("Descrição"));
        paneDescricao.setPreferredSize(new Dimension(200,200));
        paneDescricao.setMaximumSize(new Dimension(200,200));
        painelInfo.setBorder(BorderFactory.createTitledBorder("Informações da Consulta"));
        painelInfo.setLayout(new BorderLayout(10,10));
        painelInfo.add(painelInfoLabel, BorderLayout.WEST);
        painelInfo.add(painelInfoText, BorderLayout.CENTER);
        painelInfo.add(new JScrollPane(paneDescricao), BorderLayout.SOUTH);


        painelPrincipal.setLayout(new BorderLayout(5,5));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        painelPrincipal.add(painelSuperior, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        frame.add(painelPrincipal);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
