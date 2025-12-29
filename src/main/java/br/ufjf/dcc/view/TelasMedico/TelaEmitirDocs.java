package br.ufjf.dcc.view.TelasMedico;
import javax.swing.*;
import java.awt.*;

public class TelaEmitirDocs {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelBotoes;
    private JPanel painelInfo;
    private JPanel painelLabel;
    private JPanel painelBox;
    private JPanel painelDocumento;
    private JTextPane campoDocumento;
    private JTextField campoPaciente;
    private JComboBox<String> boxTipo;
    private JLabel labelPaciente;
    private JLabel labelTipo;
    private JButton botaoSair;
    private JButton botaoEmitir;

    public TelaEmitirDocs(){
        frame = new JFrame("Emitir Documentos");
        painelPrincipal = new JPanel();
        painelBotoes = new JPanel();
        painelInfo = new JPanel();
        painelLabel = new JPanel();
        painelBox = new JPanel();
        painelDocumento = new JPanel();
        campoDocumento = new JTextPane();
        campoPaciente = new JTextField();
        boxTipo = new JComboBox<String>();
            boxTipo.addItem("");
            boxTipo.addItem("Atestado");
            boxTipo.addItem("Receita");
            boxTipo.addItem("Exame");
        painelDocumento  = new JPanel();
        labelPaciente = new JLabel("Paciente:");
        labelTipo = new JLabel("Tipo de Documento:");
        botaoSair = new JButton("Sair");
        botaoEmitir = new JButton("Emitir");
    }
    
    public void abrirTelaEmitirDocs(){
        frame.setSize(800,600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        botaoSair.addActionListener(e -> frame.dispose());

        campoDocumento.setBorder(BorderFactory.createTitledBorder("Documento"));
        campoDocumento.setPreferredSize(new Dimension(350,300));

        painelBotoes.setLayout(new GridLayout(0,2,10,0));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoEmitir);

        painelLabel.setLayout(new GridLayout(2,0,0,10));
        painelLabel.add(labelPaciente);
        painelLabel.add(labelTipo);

        painelBox.setLayout(new GridLayout(2,0,0,10));
        painelBox.add(campoPaciente);
        painelBox.add(boxTipo);

        painelInfo.setLayout(new BorderLayout(10,10));
        painelInfo.setBorder(BorderFactory.createEmptyBorder(0,0,450,0));
        painelInfo.add(painelLabel, BorderLayout.WEST);
        painelInfo.add(painelBox, BorderLayout.CENTER);

        painelPrincipal.setLayout(new BorderLayout(10,10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
        painelPrincipal.add(painelInfo, BorderLayout.CENTER);
        painelPrincipal.add(new JScrollPane(campoDocumento), BorderLayout.WEST);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
