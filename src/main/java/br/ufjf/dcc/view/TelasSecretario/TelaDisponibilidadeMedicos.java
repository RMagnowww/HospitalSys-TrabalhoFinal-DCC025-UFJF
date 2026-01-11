/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */
    
package br.ufjf.dcc.view.TelasSecretario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import br.ufjf.dcc.model.Medico;
import br.ufjf.dcc.model.Persistencia;
import br.ufjf.dcc.controller.AgendamentoController;
import br.ufjf.dcc.model.Consulta;
import br.ufjf.dcc.model.enums.StatusMedico;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
    private JTextField campoEspecialidade;
    private JTextField campoMedico2;
    private JTextField campoPaciente;
    private JTextField campoHorario;
    private JComboBox<LocalTime> boxHorarios;
    private JLabel labelMedico1;
    private JLabel labelEspecialidade;
    private JLabel labelMedico2;
    private JLabel labelPaciente;
    private JLabel labelHorarioConsulta;
    private JLabel labelHorariosDisponiveis;
    private JButton botaoSair;
    private ArrayList<Consulta> consultas;
    private ArrayList<Medico> medicos;
    private ArrayList<Medico> medicosDisponiveis;

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
        campoMedico1 = new JTextField(20);
        campoEspecialidade = new JTextField(20);
        campoMedico2 = new JTextField(20);
        campoPaciente = new JTextField(20);
        campoHorario = new JTextField(20);
        campoMedico1.setEditable(false);
        campoEspecialidade.setEditable(false);
        campoMedico2.setEditable(false);
        campoPaciente.setEditable(false);
        campoHorario.setEditable(false);
        boxHorarios = new JComboBox<LocalTime>();
        labelMedico1 = new JLabel("Médico:");
        labelEspecialidade = new JLabel("Especialidade:");
        labelMedico2 = new JLabel("Médico:");
        labelPaciente = new JLabel("Paciente:");
        labelHorarioConsulta = new JLabel("Horário:");
        labelHorariosDisponiveis = new JLabel("Horários Disponíveis:");
        botaoSair = new JButton("Sair");
    }

    public void abrirTelaDisponibilidadeMedicos(){
        frame.setSize(600,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(10,10));

        try{
            medicos = Persistencia.carregarMedicos();
            medicosDisponiveis = new ArrayList<>();
            for(Medico m : medicos)
                if(m.getAtividade().equals(StatusMedico.ATIVO))
                    medicosDisponiveis.add(m);
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
        listMedicosAtivos.setListData(medicosDisponiveis.toArray(new Medico[medicosDisponiveis.size()]));

        listMedicosAtivos.addListSelectionListener(e ->{
            campoMedico1.setText(listMedicosAtivos.getSelectedValue().getNome());
            campoEspecialidade.setText(listMedicosAtivos.getSelectedValue().getEspecialidade());
            atualizarHorariosDisponiveis();

        });

        try{
            consultas = Persistencia.carregarConsultasFalta();
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
        listConsultaFalta.setListData(consultas.toArray(new Consulta[consultas.size()]));

        listConsultaFalta.addListSelectionListener(e ->{
            campoPaciente.setText(listConsultaFalta.getSelectedValue().getPaciente().getNome());
            campoMedico2.setText(listConsultaFalta.getSelectedValue().getMedico().getNome());
            campoHorario.setText(listConsultaFalta.getSelectedValue().getDataHora().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")));

        });

        botaoSair.addActionListener(e -> frame.dispose());

        listMedicosAtivos.setBorder(BorderFactory.createTitledBorder("Medicos Disponíveis"));
        listConsultaFalta.setBorder(BorderFactory.createTitledBorder("Consultas com falta"));

        painelLists.setLayout(new GridLayout(2,0,0,15));
        painelLists.add(new JScrollPane(listMedicosAtivos));
        painelLists.add(new JScrollPane(listConsultaFalta));

        painelLabels1.setLayout(new GridLayout(3,0,0,10));
        painelLabels1.add(labelMedico1);
        painelLabels1.add(labelEspecialidade);
        painelLabels1.add(labelHorariosDisponiveis);

        painelLabels2.setLayout(new GridLayout(3,0,0,10));
        painelLabels2.add(labelMedico2);
        painelLabels2.add(labelPaciente);
        painelLabels2.add(labelHorarioConsulta);

        painelBox1.setLayout(new GridLayout(3,0,0,10));
        painelBox1.add(campoMedico1);
        painelBox1.add(campoEspecialidade);
        painelBox1.add(boxHorarios);

        painelBox2.setLayout(new GridLayout(3,0,0,10));
        painelBox2.add(campoMedico2);
        painelBox2.add(campoPaciente);
        painelBox2.add(campoHorario);

        painelInfo1.setLayout(new BorderLayout(10,10));
        painelInfo1.setBorder(BorderFactory.createEmptyBorder(0,0,100,0));
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
        painelSuperior.add(painelLists, BorderLayout.CENTER);
        painelSuperior.add(painelInfoMain, BorderLayout.EAST);

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

    private void atualizarHorariosDisponiveis() {
        boxHorarios.removeAllItems();
        Medico medicoSelecionado = listMedicosAtivos.getSelectedValue();
        LocalDate data = LocalDate.now();

        if (medicoSelecionado != null && data != null) {
            try {
                List<LocalTime> horarios = AgendamentoController.gerarHorariosDisponiveis(medicoSelecionado, data);
                for (LocalTime horario : horarios) {
                    boxHorarios.addItem(horario);
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
}
