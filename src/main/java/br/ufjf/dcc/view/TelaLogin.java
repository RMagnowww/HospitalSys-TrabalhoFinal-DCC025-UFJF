package br.ufjf.dcc.view;
import javax.swing.*;
import java.awt.*;

public class TelaLogin {
    private final JFrame frame;
    private final JPanel painelPrincipal;
    private final JPanel painelEsq;
    private final JPanel painelDir;
    private final JPanel painelBotoes;
    private final JTextField campoEmail;
    private final JTextField campoSenha;
    private final JLabel labelEmail;
    private final JLabel labelSenha;
    private final JButton botaoEntrar;
    private final JButton botaoCadastrar;

    public TelaLogin(){
        frame =  new JFrame("Login");
        painelPrincipal = new JPanel();
        painelEsq = new JPanel();
        painelDir = new JPanel();
        painelBotoes = new JPanel();
        campoEmail = new JTextField(26);
        campoSenha = new JTextField(26);
        labelEmail = new JLabel("E-mail:");
        labelSenha = new JLabel("Senha:");
        botaoEntrar = new JButton("Entrar");
        botaoCadastrar = new JButton("Fazer Cadastro");
    }

    public void abrirLogin(){
        frame.setSize(400,200);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painelPrincipal.setLayout(new BorderLayout(0,5));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));
        painelEsq.setLayout(new GridLayout(0,1,0,20));
        painelEsq.setBorder(BorderFactory.createEmptyBorder(15,0,21,0));
        painelDir.setLayout(new GridLayout(0,1,0,20));
        painelDir.setBorder(BorderFactory.createEmptyBorder(15,0,21,0));
        painelBotoes.setLayout(new GridLayout(1,2,10,0));

        //botaoEntrar.addActionListener(); - adicionar ação de login aqui
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelaCadastroUsuario telaCadastro = new TelaCadastroUsuario();
                telaCadastro.abrirCadastroUsuario();
            }
        });

        painelEsq.add(labelEmail);
        painelEsq.add(labelSenha);

        painelDir.add(campoEmail);
        painelDir.add(campoSenha);

        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(botaoEntrar);

        painelPrincipal.add(painelEsq, BorderLayout.WEST);
        painelPrincipal.add(painelDir, BorderLayout.EAST);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}