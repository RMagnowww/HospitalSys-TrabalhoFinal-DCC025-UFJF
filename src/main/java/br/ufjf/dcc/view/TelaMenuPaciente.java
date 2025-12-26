package br.ufjf.dcc.view;
import br.ufjf.dcc.model.Paciente;
import javax.swing.*;
import java.awt.*;

public class TelaMenuPaciente {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JButton agendaConsulta;
    private JButton docsMedicos;
    private JButton historico;
    private JButton dadosPessoais;
    private JButton verificaVisita;
    private JButton botaoSair;

    public TelaMenuPaciente() {
        frame = new JFrame("Menu - Paciente");
        painelPrincipal = new JPanel();
        painelEsq = new JPanel(); 
        painelDir = new JPanel();  
        agendaConsulta = new JButton("Agendamento de Consultas");
        docsMedicos = new JButton("Documentos Médicos");
        historico = new JButton("Histórico Médico");
        dadosPessoais = new JButton("Dados Pessoais");
        verificaVisita = new JButton("Verificar Disponibilidade de Visita");
        botaoSair = new JButton("Sair");
    }

    public void abrirMenuPaciente(Paciente paciente){
        frame.setSize(800,450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        agendaConsulta.addActionListener(e -> {
            TelaAgendamento telaAgendamento = new TelaAgendamento();
            telaAgendamento.abrirTelaAgendamento();
        });
        dadosPessoais.addActionListener(e -> {
            TelaCadastroUsuario telaCadastroUsuario = new TelaCadastroUsuario();
            telaCadastroUsuario.abrirDadosPaciente(paciente);
        });
        botaoSair.addActionListener(e -> frame.dispose());

        painelPrincipal.setLayout(new GridLayout(0, 2, 25, 0));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(45,35,45,35));

        painelEsq.setLayout(new GridLayout(0,1,0,20));
        painelEsq.add(agendaConsulta);
        painelEsq.add(docsMedicos);
        painelEsq.add(historico);

        painelDir.setLayout(new GridLayout(0,1,0,20));
        painelDir.add(dadosPessoais);
        painelDir.add(verificaVisita);
        painelDir.add(botaoSair);

        painelPrincipal.add(painelEsq);
        painelPrincipal.add(painelDir);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
