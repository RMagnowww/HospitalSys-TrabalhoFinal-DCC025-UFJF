package br.ufjf.dcc.view;
import javax.swing.*;
import java.awt.*;

public class TelaMenuMedico {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JButton botaoHistoricoClinico;
    private JButton emitirDocs;
    private JButton botaoAgenda;
    private JButton botaoSair;

    public TelaMenuMedico() {
        frame = new JFrame("Menu - Médico");
        painelPrincipal = new JPanel();
        painelEsq = new JPanel(); 
        painelDir = new JPanel();  
        botaoHistoricoClinico = new JButton("Histórico Clínico");
        emitirDocs = new JButton("Emissão de Documentos");
        botaoAgenda = new JButton("Agenda de Atendimentos");
        botaoSair = new JButton("Sair");
    }

    public void abrirMenuMedico(){
        frame.setSize(800,350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        
        botaoHistoricoClinico.addActionListener(e -> {
            TelaHistoricoClinico telaHistoricoClinico = new TelaHistoricoClinico();
            telaHistoricoClinico.abrirHistoricoClinico();
        });
        botaoAgenda.addActionListener(e -> {
            TelaAgenda telaAgenda = new TelaAgenda();
            telaAgenda.abrirAgenda();
        });
        botaoSair.addActionListener(e -> frame.dispose());

        painelPrincipal.setLayout(new GridLayout(0, 2, 25, 0));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(45,35,45,35));

        painelEsq.setLayout(new GridLayout(0,1,0,25));
        painelEsq.add(botaoHistoricoClinico);
        painelEsq.add(emitirDocs);

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

