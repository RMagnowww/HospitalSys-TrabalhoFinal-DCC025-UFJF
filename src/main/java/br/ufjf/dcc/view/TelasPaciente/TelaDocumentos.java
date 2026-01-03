package br.ufjf.dcc.view.TelasPaciente;
import br.ufjf.dcc.model.Paciente;
import br.ufjf.dcc.model.DocumentoMedico;
import br.ufjf.dcc.model.Persistencia;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TelaDocumentos{
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelSuperior;
    private JPanel painelInferior;
    private JList<DocumentoMedico> listDocumentos;
    private JTextPane painelVisualizar;
    private JButton botaoSair;
    private ArrayList<DocumentoMedico> documentos; 

    public TelaDocumentos(){
        frame = new JFrame("Documentos Médicos");
        painelPrincipal = new JPanel();
        painelSuperior = new JPanel();
        painelInferior = new JPanel();
        listDocumentos = new JList<DocumentoMedico>();
        painelVisualizar = new JTextPane();
        botaoSair = new JButton("Sair");
    }
    public void abrirTelaDocumentos(Paciente paciente){
        frame.setSize(600,450);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listDocumentos.setBorder(BorderFactory.createTitledBorder("Documentos Médicos"));
        try{
            documentos = Persistencia.carregarDocumentosPaciente(paciente);
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
        listDocumentos.setListData(documentos.toArray(new DocumentoMedico[documentos.size()]));
        painelVisualizar.setBorder(BorderFactory.createTitledBorder("Visualizador"));
        painelVisualizar.setEditable(false);
        listDocumentos.addListSelectionListener(e -> {
            if(listDocumentos.getSelectedValue() != null)
                painelVisualizar.setText(listDocumentos.getSelectedValue().gerarConteudo());
        });
        botaoSair.addActionListener(e -> frame.dispose());

        painelSuperior.setLayout(new GridLayout(0,2,10,0));
        painelSuperior.add(new JScrollPane(listDocumentos));
        painelSuperior.add(new JScrollPane(painelVisualizar));


        painelInferior.setLayout(new GridLayout(0,1,10,0));
        painelInferior.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
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