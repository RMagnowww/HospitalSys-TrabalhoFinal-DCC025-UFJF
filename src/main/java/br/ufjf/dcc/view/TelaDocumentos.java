package br.ufjf.dcc.view;
import br.ufjf.dcc.model.Atestado;
import br.ufjf.dcc.model.Exame;
import javax.swing.*;
import java.awt.*;

public class TelaDocumentos{
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelSuperior;
    private JPanel painelInferior;
    private JList<Exame> listExames;
    private JList<Atestado> listAtestados;
    private JButton botaoSair;

    public TelaDocumentos(){
        frame = new JFrame("Documentos Médicos");
        painelPrincipal = new JPanel();
        painelSuperior = new JPanel();
        painelInferior = new JPanel();
        listExames = new JList<Exame>();
        listAtestados = new JList<Atestado>();
        botaoSair = new JButton("Sair");
    }
    public void abrirTelaDocumentos(){
        frame.setSize(600,450);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listExames.setBorder(BorderFactory.createTitledBorder("Resultados de Exames"));
        listAtestados.setBorder(BorderFactory.createTitledBorder("Atestados Médicos"));
        botaoSair.addActionListener(e -> frame.dispose());

        painelSuperior.setLayout(new GridLayout(0,2,10,0));
        painelSuperior.add(new JScrollPane(listExames));
        painelSuperior.add(new JScrollPane(listAtestados));

        painelInferior.setLayout(new BorderLayout(0,0));
        painelInferior.setBorder(BorderFactory.createEmptyBorder(0,150,0,150));
        painelInferior.add(botaoSair);

        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        painelPrincipal.setLayout(new BorderLayout(5,5));
        painelPrincipal.add (painelSuperior, BorderLayout.CENTER);
        painelPrincipal.add (painelInferior,BorderLayout.SOUTH);

        
        frame.add(painelPrincipal);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}