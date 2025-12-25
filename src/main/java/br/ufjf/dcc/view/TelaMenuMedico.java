package br.ufjf.dcc.view;
import javax.swing.*;
import java.awt.*;

public class TelaMenuMedico {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JButton historicoClinico;
    private JButton emitirDocs;
    private JButton Prontuario;
    private JButton agenda;
    private JButton botaoSair;

    public TelaMenuMedico() {
        frame = new JFrame("Menu - Médico");
        painelPrincipal = new JPanel();
        painelEsq = new JPanel(); 
        painelDir = new JPanel();  
        historicoClinico = new JButton("Histórico Clínico");
        emitirDocs = new JButton("Emissão de Documentos");
        Prontuario = new JButton("Prontuário");
        agenda = new JButton("Agenda de Atendimentos");
        botaoSair = new JButton("Sair");
    }

    public void abrirMenuMedico(){
        frame.setSize(800,450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        
        botaoSair.addActionListener(e -> frame.dispose());

        painelPrincipal.setLayout(new GridLayout(0, 2, 25, 0));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(45,35,45,35));

        painelEsq.setLayout(new GridLayout(0,1,0,25));
        painelEsq.add(historicoClinico);
        painelEsq.add(emitirDocs);
        painelEsq.add(Prontuario);

        painelDir.setLayout(new GridLayout(0,1,0,25));
        painelDir.setBorder(BorderFactory.createEmptyBorder(0,0,115,0));
        painelDir.add(agenda);
        painelDir.add(botaoSair);

        painelPrincipal.add(painelEsq);
        painelPrincipal.add(painelDir);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}

