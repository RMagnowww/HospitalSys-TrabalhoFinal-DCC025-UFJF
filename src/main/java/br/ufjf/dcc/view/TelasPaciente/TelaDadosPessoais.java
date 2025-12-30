package br.ufjf.dcc.view.TelasPaciente;
import br.ufjf.dcc.controller.PacienteController;
import br.ufjf.dcc.model.Paciente;
import javax.swing.*;
import java.awt.*;

public class TelaDadosPessoais {
    private JFrame frame;
    private JPanel painelCadastrar;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JPanel painelBotoes;
    private JTextField campoNome;
    private JTextField campoCPF;
    private JTextField campoTelefone;
    private JTextField campoCidade;
    private JTextField campoBairro;
    private JTextField campoRua;
    private JTextField campoNumero;
    private JTextField campoCEP;
    private JTextField campoDataNascimento;
    private JTextField campoTipoSanguineo;
    private JTextField campoEmail;
    private JTextField campoSenha;
    private JButton botaoConfirmar;
    private JButton botaoSair;
    private JLabel labelNome;
    private JLabel labelCPF;
    private JLabel labelTelefone;
    private JLabel labelCidade;
    private JLabel labelBairro;
    private JLabel labelRua;
    private JLabel labelNumero;
    private JLabel labelCEP;
    private JLabel labelDataNascimento;
    private JLabel labelTipoSanguineo;
    private JLabel labelEmail;
    private JLabel labelSenha;

    public TelaDadosPessoais(){
        frame =  new JFrame("Cadastro de Pacientes");
        painelCadastrar = new JPanel();
        painelEsq = new JPanel();
        painelDir = new JPanel();
        painelBotoes = new JPanel();
        campoNome = new JTextField(23);
        campoCPF = new JTextField(23);
        campoTelefone = new JTextField(23);
        campoCidade = new JTextField(23);
        campoBairro = new JTextField(23);
        campoRua = new JTextField(23);
        campoNumero = new JTextField(23);
        campoCEP = new JTextField(23);
        campoDataNascimento= new JTextField(23);
        campoTipoSanguineo = new JTextField(23);
        campoEmail = new JTextField(23);
        campoSenha = new JTextField(23);
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
        labelDataNascimento = new JLabel("Data de Nascimento:");
        labelTipoSanguineo = new JLabel("Tipo Sanguíneo");
        labelEmail = new JLabel("E-mail:");
        labelSenha = new JLabel("Senha:");
    }

    public void abrirTelaDadosPessoais(Paciente paciente){
        campoNome.setText(paciente.getNome());
        campoCPF.setText(paciente.getCpf());
        campoTelefone.setText(paciente.getTelefone());
        campoCidade.setText(paciente.getEndereco().getCidade());
        campoBairro.setText(paciente.getEndereco().getBairro());
        campoRua.setText(paciente.getEndereco().getRua());
        campoNumero.setText(paciente.getEndereco().getNumero());
        campoCEP.setText(paciente.getEndereco().getCep());
        campoDataNascimento.setText(paciente.getDataNascimento());
        campoTipoSanguineo.setText(paciente.getTipoSanguineo());
        campoEmail.setText(paciente.getEmail());
        campoSenha.setText(paciente.getSenha());

        frame.setSize(450,550);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        painelCadastrar.setLayout(new BorderLayout(10,10));
        painelCadastrar.setBorder(BorderFactory.createEmptyBorder(15,23,15,23));

        painelEsq.setLayout(new GridLayout(0,1,0,15));
        painelEsq.setBorder(BorderFactory.createEmptyBorder(15,0,23,0));

        painelDir.setLayout(new GridLayout(0,1,0,15));
        painelDir.setBorder(BorderFactory.createEmptyBorder(15,0,23,0));

        painelBotoes.setLayout(new GridLayout(1,2,10,0));

        botaoConfirmar.addActionListener(e -> { PacienteController.alterarDados(paciente, campoNome.getText(),campoCPF.getText(),campoTelefone.getText(),campoCidade.getText(),campoBairro.getText(),campoRua.getText(),campoNumero.getText(),campoCEP.getText(),campoEmail.getText(),campoSenha.getText(),campoDataNascimento.getText(),campoTipoSanguineo.getText());});
        botaoSair.addActionListener(e -> frame.dispose());
        painelEsq.add(labelNome);
        painelEsq.add(labelCPF);
        painelEsq.add(labelTelefone);
        painelEsq.add(labelCidade);
        painelEsq.add(labelBairro);
        painelEsq.add(labelRua);
        painelEsq.add(labelNumero);
        painelEsq.add(labelCEP);
        painelEsq.add(labelDataNascimento);
        painelEsq.add(labelTipoSanguineo);
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
        painelDir.add(campoDataNascimento);
        painelDir.add(campoTipoSanguineo);
        painelDir.add(campoEmail);
        painelDir.add(campoSenha);

        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoConfirmar);

        painelCadastrar.add(painelEsq, BorderLayout.WEST);
        painelCadastrar.add(painelDir, BorderLayout.EAST);
        painelCadastrar.add(painelBotoes, BorderLayout.SOUTH);

        frame.add(painelCadastrar);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
