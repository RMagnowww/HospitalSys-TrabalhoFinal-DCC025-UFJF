package br.ufjf.dcc.view.TelasMedico;
import javax.swing.*;
import br.ufjf.dcc.model.Medico;
import java.awt.*;

public class TelaMenuMedico {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JButton botaoHistoricoClinico;
    private JButton botaoEmitirDocs;
    private JButton botaoAgenda;
    private JButton botaoSair;

    public TelaMenuMedico() {
        frame = new JFrame("Menu - Médico");
        painelPrincipal = new JPanel();
        painelEsq = new JPanel(); 
        painelDir = new JPanel();  
        botaoHistoricoClinico = new JButton("Histórico Clínico");
        botaoEmitirDocs = new JButton("Emissão de Documentos");
        botaoAgenda = new JButton("Agenda de Atendimentos");
        botaoSair = new JButton("Sair");
    }

    public void abrirTelaMenuMedico(Medico medico){
        frame.setSize(800,350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        
        botaoHistoricoClinico.addActionListener(e -> {
            TelaHistoricoClinico telaHistoricoClinico = new TelaHistoricoClinico();
            telaHistoricoClinico.abrirTelaHistoricoClinico();
        });
        botaoEmitirDocs.addActionListener(e -> {
            TelaEmitirDocs telaEmitirDocs = new TelaEmitirDocs();
            telaEmitirDocs.abrirTelaEmitirDocs();
        });
        botaoAgenda.addActionListener(e -> {
            TelaAgenda telaAgenda = new TelaAgenda();
            telaAgenda.abrirTelaAgenda();
        });
        botaoSair.addActionListener(e -> frame.dispose());

        painelPrincipal.setLayout(new GridLayout(0, 2, 25, 0));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(45,35,45,35));

        painelEsq.setLayout(new GridLayout(0,1,0,25));
        painelEsq.add(botaoHistoricoClinico);
        painelEsq.add(botaoEmitirDocs);

        painelDir.setLayout(new GridLayout(0,1,0,25));
        painelDir.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        painelDir.add(botaoAgenda);
        painelDir.add(botaoSair);

        painelPrincipal.add(painelEsq);
        painelPrincipal.add(painelDir);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}

