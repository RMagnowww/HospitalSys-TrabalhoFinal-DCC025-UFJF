package br.ufjf.dcc.view.TelasPaciente;
import br.ufjf.dcc.model.Paciente;

import javax.swing.*;
import java.awt.*;

public class TelaMenuPaciente {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JButton botaoAgendarConsulta;
    private JButton botaoDocsMedicos;
    private JButton botaoHistorico;
    private JButton botaoDadosPessoais;
    private JButton botaoVerificaVisita;
    private JButton botaoSair;

    public TelaMenuPaciente() {
        frame = new JFrame("Menu - Paciente");
        painelPrincipal = new JPanel();
        painelEsq = new JPanel(); 
        painelDir = new JPanel();  
        botaoAgendarConsulta = new JButton("Agendamento de Consultas");
        botaoDocsMedicos = new JButton("Documentos Médicos");
        botaoHistorico = new JButton("Histórico Médico");
        botaoDadosPessoais = new JButton("Dados Pessoais");
        botaoVerificaVisita = new JButton("Verificar Disponibilidade de Visita");
        botaoSair = new JButton("Sair");
    }

    public void abrirTelaMenuPaciente(Paciente paciente){
        frame.setSize(800,450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        botaoAgendarConsulta.addActionListener(e -> {
            TelaAgendamento telaAgendamento = new TelaAgendamento();
            telaAgendamento.abrirTelaAgendamento(paciente);
        });
        botaoDocsMedicos.addActionListener(e -> {
            TelaDocumentos telaDocumentos = new TelaDocumentos();
            telaDocumentos.abrirTelaDocumentos(paciente);
        });
        botaoHistorico.addActionListener(e -> {
            TelaHistorico telaHistorico = new TelaHistorico();
            telaHistorico.abrirTelaHistorico(paciente);
        });
        botaoDadosPessoais.addActionListener(e -> {
            TelaDadosPessoais telaDadosPessoais = new TelaDadosPessoais();
            telaDadosPessoais.abrirTelaDadosPessoais(paciente);
        });
        botaoVerificaVisita.addActionListener(e -> {
            TelaDisponibilidadeVisita telaDisponibilidadeVisita = new TelaDisponibilidadeVisita();
            telaDisponibilidadeVisita.abrirTelaDisponibilidadeVisita();
        });
        botaoSair.addActionListener(e -> frame.dispose());

        painelPrincipal.setLayout(new GridLayout(0, 2, 25, 0));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(45,35,45,35));

        painelEsq.setLayout(new GridLayout(0,1,0,20));
        painelEsq.add(botaoAgendarConsulta);
        painelEsq.add(botaoDocsMedicos);
        painelEsq.add(botaoHistorico);

        painelDir.setLayout(new GridLayout(0,1,0,20));
        painelDir.add(botaoDadosPessoais);
        painelDir.add(botaoVerificaVisita);
        painelDir.add(botaoSair);

        painelPrincipal.add(painelEsq);
        painelPrincipal.add(painelDir);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
