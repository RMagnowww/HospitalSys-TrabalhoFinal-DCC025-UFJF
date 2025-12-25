package br.ufjf.dcc.view;
import javax.swing.*;
import java.awt.*;

public class TelaMenuSecretario {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JButton cadastro;
    private JButton visitasInfo;
    private JButton corpoClinico;
    private JButton disponibilidade;
    private JButton botaoSair;

    public TelaMenuSecretario() {
        frame = new JFrame("Menu - Secretário");
        painelPrincipal = new JPanel();
        painelEsq = new JPanel(); 
        painelDir = new JPanel();  
        cadastro = new JButton("Cadastro de Pacientes");
        visitasInfo = new JButton("Visitas e Informações");
        corpoClinico = new JButton("Corpo Clínico");
        disponibilidade = new JButton("Disponibilidade");
        botaoSair = new JButton("Sair");
    }

    public void abrirMenuSecretario(){
        frame.setSize(800,450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        cadastro.addActionListener(e -> {
            TelaCadastroUsuario telaCadastroPaciente = new TelaCadastroUsuario();
            telaCadastroPaciente.abrirCadastroUsuario();
        });
        botaoSair.addActionListener(e -> frame.dispose());

        painelPrincipal.setLayout(new GridLayout(0, 2, 25, 0));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(45,35,45,35));

        painelEsq.setLayout(new GridLayout(0,1,0,25));
        painelEsq.add(cadastro);
        painelEsq.add(visitasInfo);
        painelEsq.add(corpoClinico);

        painelDir.setLayout(new GridLayout(0,1,0,25));
        painelDir.setBorder(BorderFactory.createEmptyBorder(0,0,115,0));
        painelDir.add(disponibilidade);
        painelDir.add(botaoSair);

        painelPrincipal.add(painelEsq);
        painelPrincipal.add(painelDir);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
