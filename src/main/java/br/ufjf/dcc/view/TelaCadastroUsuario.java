package br.ufjf.dcc.view;
import br.ufjf.dcc.model.Paciente;
import javax.swing.*;
import java.awt.*;

public class TelaCadastroUsuario {
    private final JFrame frame;
    private final JPanel painelPrincipal;
    private final JPanel painelEsq;
    private final JPanel painelDir;
    private final JTextField campoNome;
    private final JTextField campoCPF;
    private final JTextField campoTelefone;
    private final JTextField campoCidade;
    private final JTextField campoBairro;
    private final JTextField campoRua;
    private final JTextField campoNumero;
    private final JTextField campoCEP;
    private final JTextField campoEmail;
    private final JTextField campoSenha;
    private final JButton botaoCadastrar;
    private final JButton botaoConfirmar;
    private final JButton botaoSair;
    private final JLabel labelNome;
    private final JLabel labelCPF;
    private final JLabel labelTelefone;
    private final JLabel labelCidade;
    private final JLabel labelBairro;
    private final JLabel labelRua;
    private final JLabel labelNumero;
    private final JLabel labelCEP;
    private final JLabel labelEmail;
    private final JLabel labelSenha;

    public TelaCadastroUsuario(){
        frame =  new JFrame("Cadastro");
        painelPrincipal = new JPanel();
        painelEsq = new JPanel();
        painelDir = new JPanel();
        campoNome = new JTextField(23);
        campoCPF = new JTextField(23);
        campoTelefone = new JTextField(23);
        campoCidade = new JTextField(23);
        campoBairro = new JTextField(23);
        campoRua = new JTextField(23);
        campoNumero = new JTextField(23);
        campoCEP = new JTextField(23);
        campoEmail = new JTextField(23);
        campoSenha = new JTextField(23);
        botaoCadastrar = new JButton("Cadastrar");
        botaoConfirmar = new JButton("Confirmar Mudanças");
        botaoSair = new JButton("Sair");
        labelNome = new JLabel("Nome:");
        labelCPF = new JLabel("CPF:");
        labelTelefone = new JLabel("Telefone:");
        labelCidade = new JLabel("Cidade:");
        labelBairro = new JLabel("Bairro:");
        labelRua = new JLabel("Rua:");
        labelNumero = new JLabel("Número:");
        labelCEP = new JLabel("CEP:");
        labelEmail = new JLabel("E-mail:");
        labelSenha = new JLabel("Senha:");
    }

    public void abrirCadastroUsuario(){
        frame.setSize(400,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        painelPrincipal.setLayout(new BorderLayout(10,10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15,23,15,23));
        painelEsq.setLayout(new GridLayout(0,1,0,15));
        painelEsq.setBorder(BorderFactory.createEmptyBorder(15,0,23,0));
        painelDir.setLayout(new GridLayout(0,1,0,15));
        painelDir.setBorder(BorderFactory.createEmptyBorder(15,0,23,0));
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1,2,10,0));

        //botaoCadastrar.addActionListener(); -  Adicionar ação de cadastro aqui
        botaoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frame.dispose();
            }
        });
        painelEsq.add(labelNome);
        painelEsq.add(labelCPF);
        painelEsq.add(labelTelefone);
        painelEsq.add(labelCidade);
        painelEsq.add(labelBairro);
        painelEsq.add(labelRua);
        painelEsq.add(labelNumero);
        painelEsq.add(labelCEP);
        painelEsq.add(labelEmail);
        painelEsq.add(labelSenha);

        painelDir.add(campoNome);
        painelDir.add(campoCPF);
        painelDir.add(campoTelefone);
        painelDir.add(campoCidade);
        painelDir.add(campoBairro);
        painelDir.add(campoRua);
        painelDir.add(campoNumero);
        painelDir.add(campoCEP);
        painelDir.add(campoEmail);
        painelDir.add(campoSenha);

        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoCadastrar);

        painelPrincipal.add(painelEsq, BorderLayout.WEST);
        painelPrincipal.add(painelDir, BorderLayout.EAST);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

public void abrirDadosPaciente(Paciente paciente){
        campoNome.setText(paciente.getNome());
        campoCPF.setText(paciente.getCpf());
        campoTelefone.setText(paciente.getTelefone());
        campoCidade.setText(paciente.getEndereco().getCidade());
        campoBairro.setText(paciente.getEndereco().getBairro());
        campoRua.setText(paciente.getEndereco().getRua());
        campoNumero.setText(paciente.getEndereco().getNumero());
        campoCEP.setText(paciente.getEndereco().getCep());
        campoEmail.setText(paciente.getEmail());
        campoSenha.setText(paciente.getSenha());

        frame.setSize(400,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        painelPrincipal.setLayout(new BorderLayout(10,10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15,23,15,23));
        painelEsq.setLayout(new GridLayout(0,1,0,15));
        painelEsq.setBorder(BorderFactory.createEmptyBorder(15,0,23,0));
        painelDir.setLayout(new GridLayout(0,1,0,15));
        painelDir.setBorder(BorderFactory.createEmptyBorder(15,0,23,0));
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1,2,10,0));

        //botaoConfirmar.addActionListener(); -  Adicionar ação de confirmação de mudanças nos dados
        botaoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frame.dispose();
            }
        });
        painelEsq.add(labelNome);
        painelEsq.add(labelCPF);
        painelEsq.add(labelTelefone);
        painelEsq.add(labelCidade);
        painelEsq.add(labelBairro);
        painelEsq.add(labelRua);
        painelEsq.add(labelNumero);
        painelEsq.add(labelCEP);
        painelEsq.add(labelEmail);
        painelEsq.add(labelSenha);

        painelDir.add(campoNome);
        painelDir.add(campoCPF);
        painelDir.add(campoTelefone);
        painelDir.add(campoCidade);
        painelDir.add(campoBairro);
        painelDir.add(campoRua);
        painelDir.add(campoNumero);
        painelDir.add(campoCEP);
        painelDir.add(campoEmail);
        painelDir.add(campoSenha);

        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoConfirmar);

        painelPrincipal.add(painelEsq, BorderLayout.WEST);
        painelPrincipal.add(painelDir, BorderLayout.EAST);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
