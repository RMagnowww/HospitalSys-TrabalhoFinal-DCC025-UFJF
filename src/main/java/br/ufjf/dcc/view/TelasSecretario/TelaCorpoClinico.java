package br.ufjf.dcc.view.TelasSecretario;
import java.util.ArrayList;
import br.ufjf.dcc.controller.SecretarioController;
import br.ufjf.dcc.model.Persistencia;
import br.ufjf.dcc.model.Medico;
import br.ufjf.dcc.model.enums.StatusMedico;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
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
    private ArrayList<Medico> medicos = new ArrayList<>();

    public TelaCorpoClinico(){
        frame =  new JFrame("Cadastro de Médicos");
        painelPrincipal = new JPanel();
        painelCadastrar = new JPanel();
        painelEsq = new JPanel();
        painelDir = new JPanel();
        painelList = new JPanel();
        painelBotoes = new JPanel();
        campoNome = new JTextField(36);
        campoCPF = new JTextField(36);
        campoTelefone = new JTextField(36);
        campoEspecialidade = new JTextField(36);
        campoCrm = new JTextField(36);
        campoEmail = new JTextField(36);
        campoSenha = new JTextField(36);
        boxStatus = new JComboBox<StatusMedico>(StatusMedico.values());
        botaoCadastrar = new JButton("Salvar Médico");
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
        labelAtividade = new JLabel("Atividade:");
        listMedicos = new JList<Medico>();
            try{
                medicos = Persistencia.carregarMedicos();
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
            listMedicos.setListData(medicos.toArray(new Medico[medicos.size()]));
    }

    

    public void abrirTelaCorpoClinico(){
        frame.setSize(800,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        painelEsq.setLayout(new GridLayout(0,1,0,15));
        painelEsq.setBorder(BorderFactory.createEmptyBorder(15,0,23,0));

        painelDir.setLayout(new GridLayout(0,1,0,15));
        painelDir.setBorder(BorderFactory.createEmptyBorder(15,0,23,0));

        painelBotoes.setLayout(new GridLayout(1,2,10,0));

        listMedicos.addListSelectionListener(e -> {
            if(listMedicos.getSelectedValue() != null){
                campoNome.setText(listMedicos.getSelectedValue().getNome());
                campoCPF.setText(listMedicos.getSelectedValue().getCpf());
                campoTelefone.setText(listMedicos.getSelectedValue().getTelefone());
                campoEspecialidade.setText(listMedicos.getSelectedValue().getEspecialidade());
                campoCrm.setText(listMedicos.getSelectedValue().getCrm());
                campoEmail.setText(listMedicos.getSelectedValue().getEmail());
                campoSenha.setText(listMedicos.getSelectedValue().getSenha());
                boxStatus.setSelectedItem(listMedicos.getSelectedValue().getAtividade());
            }
        });
        botaoNovo.addActionListener( e -> {
            listMedicos.clearSelection();
            campoNome.setText(null);
            campoCPF.setText(null);
            campoTelefone.setText(null);
            campoEspecialidade.setText(null);
            campoCrm.setText(null);
            campoEmail.setText(null);
            campoSenha.setText(null);
            boxStatus.setSelectedIndex(0);
        });
        botaoCadastrar.addActionListener(e -> {
                if(listMedicos.getSelectedValue() == null)
                    SecretarioController.cadastrarMedico(campoNome.getText(), campoCPF.getText(), campoTelefone.getText(), campoEmail.getText(), campoSenha.getText(), campoCrm.getText(), campoEspecialidade.getText(),  boxStatus.getSelectedItem().toString());
                else
                    SecretarioController.alterarDadosMedico(listMedicos.getSelectedValue(), campoNome.getText(), campoCPF.getText(), campoTelefone.getText(), campoCrm.getText(), campoEspecialidade.getText(), campoEmail.getText(), campoSenha.getText(), boxStatus.getSelectedItem().toString());
                try{
                    medicos = Persistencia.carregarMedicos();
                } 
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            listMedicos.setListData(medicos.toArray(new Medico[medicos.size()]));
            listMedicos.setSelectedIndex(medicos.size()-1);
        });
        botaoRemover.addActionListener(e -> {
            if(listMedicos.getSelectedValue() != null){
                SecretarioController.deletarUsuario(listMedicos.getSelectedValue());
                try{
                    medicos = Persistencia.carregarMedicos();
                } 
                catch (IOException ex) {
                    ex.printStackTrace();
                }
                listMedicos.setListData(medicos.toArray(new Medico[medicos.size()]));
                listMedicos.clearSelection();
                campoNome.setText(null);
                campoCPF.setText(null);
                campoTelefone.setText(null);
                campoEspecialidade.setText(null);
                campoCrm.setText(null);
                campoEmail.setText(null);
                campoSenha.setText(null);
                boxStatus.setSelectedIndex(0);
            }
            else
                JOptionPane.showMessageDialog(new JFrame(),"Não há médico selecionado para remoção!","Erro!", JOptionPane.ERROR_MESSAGE);
        });
        botaoSair.addActionListener(e -> frame.dispose());

        painelList.setLayout(new BorderLayout(10,10));
        painelList.setBorder(BorderFactory.createTitledBorder("Médicos Cadastrados"));
        painelList.add(new JScrollPane(listMedicos));

        painelEsq.add(labelNome);
        painelEsq.add(labelCPF);
        painelEsq.add(labelTelefone);
        painelEsq.add(labelEspecialidade);
        painelEsq.add(labelCrm);
        painelEsq.add(labelAtividade);
        painelEsq.add(labelEmail);
        painelEsq.add(labelSenha);
        

        painelDir.add(campoNome);
        painelDir.add(campoCPF);
        painelDir.add(campoTelefone);
        painelDir.add(campoEspecialidade);
        painelDir.add(campoCrm);
        painelDir.add(boxStatus);
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
        painelPrincipal.add(painelCadastrar, BorderLayout.EAST);
        painelPrincipal.add(painelList, BorderLayout.CENTER);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
