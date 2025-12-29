package br.ufjf.dcc.view.TelasSecretario;
import br.ufjf.dcc.model.Paciente;
import javax.swing.*;
import java.awt.*;

public class TelaCadastroPaciente {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelCadastrar;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JPanel painelList;
    private JPanel painelBotoes;
    private JTextField campoNome;
    private JTextField campoCPF;
    private JTextField campoTelefone;
    private JTextField campoCidade;
    private JTextField campoBairro;
    private JTextField campoRua;
    private JTextField campoNumero;
    private JTextField campoCEP;
    private JTextField campoEmail;
    private JTextField campoSenha;
    private JButton botaoCadastrar;
    private JButton botaoSair;
    private JButton botaoRemover;
    private JButton botaoNovo;
    private JLabel labelNome;
    private JLabel labelCPF;
    private JLabel labelTelefone;
    private JLabel labelCidade;
    private JLabel labelBairro;
    private JLabel labelRua;
    private JLabel labelNumero;
    private JLabel labelCEP;
    private JLabel labelEmail;
    private JLabel labelSenha;
    private JList<Paciente> listPacientes;

    public TelaCadastroPaciente(){
        frame =  new JFrame("Cadastro de Pacientes");
        painelPrincipal = new JPanel();
        painelCadastrar = new JPanel();
        painelEsq = new JPanel();
        painelDir = new JPanel();
        painelList = new JPanel();
        painelBotoes = new JPanel();
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
        botaoCadastrar = new JButton("Cadastrar Paciente");
        botaoSair = new JButton("Sair");
        botaoRemover = new JButton("Remover Paciente");
        botaoNovo = new JButton("Novo Paciente");
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
        listPacientes = new JList<Paciente>();
    }

    public void abrirTelaCadastroPaciente(){
        frame.setSize(800,520);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        painelEsq.setLayout(new GridLayout(0,1,0,15));
        painelEsq.setBorder(BorderFactory.createEmptyBorder(15,0,23,0));

        painelDir.setLayout(new GridLayout(0,1,0,15));
        painelDir.setBorder(BorderFactory.createEmptyBorder(15,0,23,0));

        painelBotoes.setLayout(new GridLayout(1,2,10,0));

        botaoCadastrar.addActionListener(e -> SecretarioController.cadastrarPaciente(campoNome.getText(), campoCPF.getText(), campoTelefone.getText(), campoCidade.getText(), campoBairro.getText(), campoRua.getText(), campoNumero.getText(), campoCEP.getText(),campoEmail.getText(),campoSenha.getText(), campoDataNascimento.getText(), campoTipoSanguineo.getText()));
        botaoSair.addActionListener(e -> frame.dispose());

        painelList.setLayout(new BorderLayout(10,10));
        painelList.setBorder(BorderFactory.createTitledBorder("Pacientes Cadastrados"));
        painelList.add(new JScrollPane(listPacientes));

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
        painelBotoes.add(botaoRemover);
        painelBotoes.add(botaoCadastrar);

        painelCadastrar.setLayout(new BorderLayout(20,10));
        painelCadastrar.add(botaoNovo, BorderLayout.NORTH);
        painelCadastrar.add(painelEsq, BorderLayout.WEST);
        painelCadastrar.add(painelDir, BorderLayout.CENTER);
        painelCadastrar.add(painelBotoes, BorderLayout.SOUTH);

        painelPrincipal.setLayout(new BorderLayout(10,10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15,23,15,23));
        painelPrincipal.add(painelCadastrar, BorderLayout.CENTER);
        painelPrincipal.add(painelList, BorderLayout.WEST);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
