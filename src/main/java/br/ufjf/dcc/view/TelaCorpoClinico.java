package br.ufjf.dcc.view;
import br.ufjf.dcc.model.Medico;
import br.ufjf.dcc.model.enums.StatusMedico;

import javax.swing.*;
import java.awt.*;
public class TelaCorpoClinico {
    private  JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelCadastrar;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JPanel painelList;
    private JPanel painelBotoes;
    private JTextField campoNome;
    private JTextField campoCPF;
    private JTextField campoTelefone;
    private JTextField campoEspecialidade;
    private JTextField campoCrm;
    private JTextField campoEmail;
    private JTextField campoSenha;
    private JComboBox<StatusMedico> boxStatus;
    private JButton botaoCadastrar;
    private JButton botaoSair;
    private JButton botaoRemover;
    private JButton botaoNovo;
    private JLabel labelNome;
    private JLabel labelCPF;
    private JLabel labelTelefone;
    private JLabel labelEspecialidade;
    private JLabel labelCrm;
    private JLabel labelEmail;
    private JLabel labelSenha;
    private JLabel labelAtividade;
    private JList<Medico> listMedicos;

    public TelaCorpoClinico(){
        frame =  new JFrame("Gerenciamento de Corpo Clínico");
        painelPrincipal = new JPanel();
        painelCadastrar = new JPanel();
        painelEsq = new JPanel();
        painelDir = new JPanel();
        painelList = new JPanel();
        painelBotoes = new JPanel();
        campoNome = new JTextField(23);
        campoCPF = new JTextField(23);
        campoTelefone = new JTextField(23);
        campoEspecialidade = new JTextField(23);
        campoCrm = new JTextField(23);
        campoEmail = new JTextField(23);
        campoSenha = new JTextField(23);
        boxStatus = new JComboBox<StatusMedico>();
        botaoCadastrar = new JButton("Cadastrar Médico");
        botaoSair = new JButton("Sair");
        botaoRemover = new JButton("Remover Médico");
        botaoNovo = new JButton("Novo Médico");
        labelNome = new JLabel("Nome:");
        labelCPF = new JLabel("CPF:");
        labelTelefone = new JLabel("Telefone:");
        labelEspecialidade = new JLabel("Especialidade:");
        labelCrm = new JLabel("CRM:");
        labelEmail = new JLabel("E-mail:");
        labelSenha = new JLabel("Senha:");
        labelAtividade = new JLabel("Status:");
        listMedicos = new JList<Medico>();
    }

    public void abrirCorpoClinico(){
        frame.setSize(800,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        painelEsq.setLayout(new GridLayout(0,1,0,15));
        painelEsq.setBorder(BorderFactory.createEmptyBorder(15,0,23,0));

        painelDir.setLayout(new GridLayout(0,1,0,15));
        painelDir.setBorder(BorderFactory.createEmptyBorder(15,0,23,0));

        painelBotoes.setLayout(new GridLayout(1,2,10,0));

        //botaoCadastrar.addActionListener(); -  Adicionar ação de cadastro aqui
        botaoSair.addActionListener(e -> frame.dispose());

        painelList.setLayout(new BorderLayout(10,10));
        painelList.setBorder(BorderFactory.createTitledBorder("Médicos Cadastrados"));
        painelList.add(new JScrollPane(listMedicos));

        painelEsq.add(labelNome);
        painelEsq.add(labelCPF);
        painelEsq.add(labelTelefone);
        painelEsq.add(labelEspecialidade);
        painelEsq.add(labelCrm);
        painelEsq.add(labelEmail);
        painelEsq.add(labelSenha);
        painelEsq.add(labelAtividade);

        painelDir.add(campoNome);
        painelDir.add(campoCPF);
        painelDir.add(campoTelefone);
        painelDir.add(campoEspecialidade);
        painelDir.add(campoCrm);
        painelDir.add(campoEmail);
        painelDir.add(campoSenha);
        painelDir.add(boxStatus);

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
