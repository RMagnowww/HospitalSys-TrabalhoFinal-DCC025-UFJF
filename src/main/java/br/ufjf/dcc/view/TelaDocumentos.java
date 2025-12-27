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
    private JTextPane painelVisualizar;
    private JButton botaoSair;
    private JButton botaoAbrir;

    public TelaDocumentos(){
        frame = new JFrame("Documentos Médicos");
        painelPrincipal = new JPanel();
        painelSuperior = new JPanel();
        painelInferior = new JPanel();
        listExames = new JList<Exame>();
        listAtestados = new JList<Atestado>();
        painelVisualizar = new JTextPane();
        botaoSair = new JButton("Sair");
        botaoAbrir = new JButton("Abrir");
    }
    public void abrirTelaDocumentos(){
        frame.setSize(800,450);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listExames.setBorder(BorderFactory.createTitledBorder("Resultados de Exames"));
        listAtestados.setBorder(BorderFactory.createTitledBorder("Atestados Médicos"));
        painelVisualizar.setBorder(BorderFactory.createTitledBorder("Visualizador"));
        painelVisualizar.setEditable(false);
        painelVisualizar.setVisible(false);
        botaoSair.addActionListener(e -> frame.dispose());

        painelSuperior.setLayout(new GridLayout(0,3,10,0));
        painelSuperior.add(new JScrollPane(listExames));
        painelSuperior.add(painelVisualizar);
        painelSuperior.add(new JScrollPane(listAtestados));

        painelInferior.setLayout(new GridLayout(0,2,10,0));
        painelInferior.setBorder(BorderFactory.createEmptyBorder(0,150,0,150));
        painelInferior.add(botaoSair);
        painelInferior.add(botaoAbrir);

        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        painelPrincipal.setLayout(new BorderLayout(5,5));
        painelPrincipal.add (painelSuperior, BorderLayout.CENTER);
        painelPrincipal.add (painelInferior,BorderLayout.SOUTH);

        
        frame.add(painelPrincipal);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}