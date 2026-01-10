/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */
    
package br.ufjf.dcc.view.TelasPaciente;
import br.ufjf.dcc.controller.PacienteController;
import br.ufjf.dcc.model.Paciente;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser; 

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
    private JDateChooser campoDataNascimento;
    private JComboBox<String> campoTipoSanguineo;  // Alterado para JComboBox
    private JComboBox<String> campoFatorRh;        // Novo JComboBox para o Fator Rh
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
            campoNome.setEditable(false);
            campoCPF.setEditable(false);
        campoTelefone = new JTextField(23);
        campoCidade = new JTextField(23);
        campoBairro = new JTextField(23);
        campoRua = new JTextField(23);
        campoNumero = new JTextField(23);
        campoCEP = new JTextField(23);
        campoDataNascimento= new JDateChooser();
        campoDataNascimento.setDateFormatString("dd/MM/yyyy");

        // Criando a JComboBox para Tipo Sanguíneo
        String[] tiposSanguineos = {"A", "B", "AB", "O"};
        campoTipoSanguineo = new JComboBox<>(tiposSanguineos);
        
        // Criando a JComboBox para Fator Rh (Positivo ou Negativo)
        String[] fatoresRh = {"+", "-"};
        campoFatorRh = new JComboBox<>(fatoresRh);

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
        try {
                    java.util.Date data = new SimpleDateFormat("dd/MM/yyyy").parse(paciente.getDataNascimento());
                    campoDataNascimento.setDate(data);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
        // Separando o tipo sanguíneo e o fator Rh
        String tipoSanguineo = paciente.getTipoSanguineo();
        String tipo = tipoSanguineo.substring(0, tipoSanguineo.length() - 1); // Parte do tipo sanguíneo (A, B, AB, O)
        String rh = tipoSanguineo.substring(tipoSanguineo.length() - 1); 
             // Parte do fator Rh (+ ou -)
        campoTipoSanguineo.setSelectedItem(tipo);
        campoFatorRh.setSelectedItem(rh);
        campoEmail.setText(paciente.getEmail());
        campoSenha.setText(paciente.getSenha());

        frame.setSize(450,650);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        painelCadastrar.setLayout(new BorderLayout(10,10));
        painelCadastrar.setBorder(BorderFactory.createEmptyBorder(15,23,15,23));

        painelEsq.setLayout(new GridLayout(0,1,0,15));
        painelEsq.setBorder(BorderFactory.createEmptyBorder(15,0,23,0));

        painelDir.setLayout(new GridLayout(0,1,0,15));
        painelDir.setBorder(BorderFactory.createEmptyBorder(15,0,23,0));

        painelBotoes.setLayout(new GridLayout(1,2,10,0));

        botaoConfirmar.addActionListener(e -> {
            if(!campoNome.getText().equals("") && !campoCPF.getText().equals("") && !campoTelefone.getText().equals("") && !campoCidade.getText().equals("") && !campoBairro.getText().equals("") && !campoRua.getText().equals("") && !campoNumero.getText().equals("") && !campoCEP.getText().equals("") && !campoEmail.getText().equals("") && !campoSenha.getText().equals("") && campoDataNascimento.getDate()!= null){ 
                // Combinação do tipo sanguíneo e fator Rh
                // Combinação do tipo sanguíneo e fator Rh
                String tipot = (String) campoTipoSanguineo.getSelectedItem();
                String rht = (String) campoFatorRh.getSelectedItem();
                String tipoSanguineoteste = tipot + rht;

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dataNascimento = dateFormat.format(campoDataNascimento.getDate());
                PacienteController.alterarDados(paciente, campoNome.getText(),campoCPF.getText(),campoTelefone.getText(),campoCidade.getText(),campoBairro.getText(),campoRua.getText(),campoNumero.getText(),campoCEP.getText(),campoEmail.getText(),campoSenha.getText(),dataNascimento ,tipoSanguineoteste);
                campoNome.setText(paciente.getNome());
                campoCPF.setText(paciente.getCpf());
                campoTelefone.setText(paciente.getTelefone());
                campoCidade.setText(paciente.getEndereco().getCidade());
                campoBairro.setText(paciente.getEndereco().getBairro());
                campoRua.setText(paciente.getEndereco().getRua());
                campoNumero.setText(paciente.getEndereco().getNumero());
                campoCEP.setText(paciente.getEndereco().getCep());
                String tipoSanguineox = paciente.getTipoSanguineo();
                String tipox = tipoSanguineox.substring(0, tipoSanguineox.length() - 1); // Parte do tipo sanguíneo (A, B, AB, O)
                String rhx = tipoSanguineox.substring(tipoSanguineox.length() - 1);  
                campoTipoSanguineo.setSelectedItem(tipox);
                campoFatorRh.setSelectedItem(rhx);
                campoEmail.setText(paciente.getEmail());
                campoSenha.setText(paciente.getSenha());
                
            }
            else
                JOptionPane.showMessageDialog(new JFrame(),"Preencha todos os campos de dados!","Erro!", JOptionPane.ERROR_MESSAGE);
        });



        botaoSair.addActionListener(e -> frame.dispose());


        JPanel painelTipoSanguineoRh = new JPanel();
        painelTipoSanguineoRh.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        painelTipoSanguineoRh.add(campoTipoSanguineo);
        painelTipoSanguineoRh.add(campoFatorRh);

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
        painelDir.add(painelTipoSanguineoRh);
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
