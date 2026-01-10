/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */

package br.ufjf.dcc.view.TelasSecretario;
import br.ufjf.dcc.controller.SecretarioController;
import br.ufjf.dcc.model.Persistencia;
import br.ufjf.dcc.model.Paciente;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import com.toedter.calendar.JDateChooser; 
import java.text.SimpleDateFormat;
import java.text.ParseException;

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
    private JDateChooser campoDataNascimento;  
    private JComboBox<String> campoTipoSanguineo;  // Alterado para JComboBox
    private JComboBox<String> campoFatorRh;        // Novo JComboBox para o Fator Rh
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
    private JLabel labelDataNascimento;
    private JLabel labelTipoSanguineo;
    private JLabel labelEmail;
    private JLabel labelSenha;
    private JList<Paciente> listPacientes;
    private ArrayList<Paciente> pacientes;

    public TelaCadastroPaciente(){
        frame =  new JFrame("Cadastro de Pacientes");
        painelPrincipal = new JPanel();
        painelCadastrar = new JPanel();
        painelEsq = new JPanel();
        painelDir = new JPanel();
        painelList = new JPanel();
        painelBotoes = new JPanel();
        campoNome = new JTextField(36);
        campoCPF = new JTextField(36);
        campoTelefone = new JTextField(36);
        campoCidade = new JTextField(36);
        campoBairro = new JTextField(36);
        campoRua = new JTextField(36);
        campoNumero = new JTextField(36);
        campoCEP = new JTextField(36);
        campoDataNascimento = new JDateChooser();  // Inicializando o JDateChooser
        campoDataNascimento.setDateFormatString("dd/MM/yyyy");  // Definindo o formato da data
        
        // Criando a JComboBox para Tipo Sanguíneo
        String[] tiposSanguineos = {"A", "B", "AB", "O"};
        campoTipoSanguineo = new JComboBox<>(tiposSanguineos);
        
        // Criando a JComboBox para Fator Rh (Positivo ou Negativo)
        String[] fatoresRh = {"+", "-"};
        campoFatorRh = new JComboBox<>(fatoresRh);
        
        campoEmail = new JTextField(36);
        campoSenha = new JTextField(36);
        botaoCadastrar = new JButton("Salvar Paciente");
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
        labelDataNascimento = new JLabel("Data de Nascimento");
        labelTipoSanguineo = new JLabel("Tipo Sanguíneo");
        labelEmail = new JLabel("E-mail:");
        labelSenha = new JLabel("Senha:");
        listPacientes = new JList<Paciente>();
            try{
                pacientes = Persistencia.carregarPacientes();
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
            listPacientes.setListData(pacientes.toArray(new Paciente[pacientes.size()]));
    }

    public void abrirTelaCadastroPaciente(){
        frame.setSize(800,700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        painelEsq.setLayout(new GridLayout(0,1,0,15));
        painelEsq.setBorder(BorderFactory.createEmptyBorder(15,0,23,0));

        painelDir.setLayout(new GridLayout(0,1,0,15));
        painelDir.setBorder(BorderFactory.createEmptyBorder(15,0,23,0));

        painelBotoes.setLayout(new GridLayout(1,2,10,0));

        listPacientes.addListSelectionListener(e -> {
            if(listPacientes.getSelectedValue() != null){
                campoNome.setEditable(false);
                campoCPF.setEditable(false);
                campoNome.setText(listPacientes.getSelectedValue().getNome());
                campoCPF.setText(listPacientes.getSelectedValue().getCpf());
                campoTelefone.setText(listPacientes.getSelectedValue().getTelefone());
                campoCidade.setText(listPacientes.getSelectedValue().getEndereco().getCidade());
                campoBairro.setText(listPacientes.getSelectedValue().getEndereco().getBairro());
                campoRua.setText(listPacientes.getSelectedValue().getEndereco().getRua());
                campoNumero.setText(listPacientes.getSelectedValue().getEndereco().getNumero());
                campoCEP.setText(listPacientes.getSelectedValue().getEndereco().getCep());
                campoEmail.setText(listPacientes.getSelectedValue().getEmail());
                campoSenha.setText(listPacientes.getSelectedValue().getSenha());
                // Alterando para pegar a data do JDateChooser
                try {
                    java.util.Date data = new SimpleDateFormat("dd/MM/yyyy").parse(listPacientes.getSelectedValue().getDataNascimento());
                    campoDataNascimento.setDate(data);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                // Separando o tipo sanguíneo e o fator Rh
                String tipoSanguineo = listPacientes.getSelectedValue().getTipoSanguineo();
                String tipo = tipoSanguineo.substring(0, tipoSanguineo.length() - 1); // Parte do tipo sanguíneo (A, B, AB, O)
                String rh = tipoSanguineo.substring(tipoSanguineo.length() - 1);      // Parte do fator Rh (+ ou -)
                campoTipoSanguineo.setSelectedItem(tipo);
                campoFatorRh.setSelectedItem(rh);
            }
        });

        botaoNovo.addActionListener( e -> {
            campoNome.setEditable(true);
            campoCPF.setEditable(true);
            listPacientes.clearSelection();
            campoNome.setText(null);
            campoCPF.setText(null);
            campoTelefone.setText(null);
            campoCidade.setText(null);
            campoBairro.setText(null);
            campoRua.setText(null);
            campoNumero.setText(null);
            campoCEP.setText(null);
            campoEmail.setText(null);
            campoSenha.setText(null); 
            campoDataNascimento.setDate(null);  // Limpando a data no JDateChooser
            campoTipoSanguineo.setSelectedIndex(0);  // Resetando o ComboBox
            campoFatorRh.setSelectedIndex(0);        // Resetando o ComboBox
        });

        botaoCadastrar.addActionListener(  e -> {
            if(!campoNome.getText().equals("") && !campoCPF.getText().equals("") && !campoTelefone.getText().equals("") && !campoCidade.getText().equals("") && !campoBairro.getText().equals("") && !campoRua.getText().equals("") && !campoNumero.getText().equals("") && !campoCEP.getText().equals("") && !campoEmail.getText().equals("") && !campoSenha.getText().equals("") && campoDataNascimento.getDate() != null){
                // Combinação do tipo sanguíneo e fator Rh
                String tipo = (String) campoTipoSanguineo.getSelectedItem();
                String rh = (String) campoFatorRh.getSelectedItem();
                String tipoSanguineo = tipo + rh;

                // Convertendo a data escolhida no JDateChooser para String
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dataNascimento = dateFormat.format(campoDataNascimento.getDate());
                
                if(listPacientes.getSelectedValue() == null)
                    SecretarioController.cadastrarPaciente(campoNome.getText(), campoCPF.getText(), campoTelefone.getText(), campoCidade.getText(), campoBairro.getText(), campoRua.getText(), campoNumero.getText(), campoCEP.getText(),campoEmail.getText(),campoSenha.getText(), dataNascimento, tipoSanguineo);
                else
                    SecretarioController.alterarDadosPaciente(listPacientes.getSelectedValue(), campoNome.getText(),campoCPF.getText(),campoTelefone.getText(),campoCidade.getText(),campoBairro.getText(),campoRua.getText(),campoNumero.getText(),campoCEP.getText(),campoEmail.getText(),campoSenha.getText(), dataNascimento, tipoSanguineo);
            }
            else
                JOptionPane.showMessageDialog(new JFrame(),"Preencha todos os campos de dados do paciente!","Erro!", JOptionPane.ERROR_MESSAGE);
            try{
                pacientes = Persistencia.carregarPacientes();
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
            listPacientes.setListData(pacientes.toArray(new Paciente[pacientes.size()]));
            //listPacientes.setSelectedIndex(pacientes.size()-1);
        });

        botaoRemover.addActionListener(e -> {
            if(listPacientes.getSelectedValue() != null){
                SecretarioController.deletarUsuario(listPacientes.getSelectedValue());
                 try{
                pacientes = Persistencia.carregarPacientes();
                } 
                catch (IOException ex) {
                ex.printStackTrace();
                }
                listPacientes.setListData(pacientes.toArray(new Paciente[pacientes.size()]));
                listPacientes.clearSelection();
                campoNome.setText(null);
                campoCPF.setText(null);
                campoTelefone.setText(null);
                campoCidade.setText(null);
                campoBairro.setText(null);
                campoRua.setText(null);
                campoNumero.setText(null);
                campoCEP.setText(null);
                campoEmail.setText(null);
                campoSenha.setText(null); 
                campoDataNascimento.setDate(null);
                campoTipoSanguineo.setSelectedIndex(0);
                campoFatorRh.setSelectedIndex(0);
            }
            else
                JOptionPane.showMessageDialog(new JFrame(),"Não há paciente selecionado para remoção!","Erro!", JOptionPane.ERROR_MESSAGE);
        });

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
        painelEsq.add(labelDataNascimento);
        painelEsq.add(labelTipoSanguineo);
        painelEsq.add(labelEmail);
        painelEsq.add(labelSenha);

        // Painel para Tipo Sanguíneo e Fator Rh
        JPanel painelTipoSanguineoRh = new JPanel();
        painelTipoSanguineoRh.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        painelTipoSanguineoRh.add(campoTipoSanguineo);
        painelTipoSanguineoRh.add(campoFatorRh);

        painelDir.add(campoNome);
        painelDir.add(campoCPF);
        painelDir.add(campoTelefone);
        painelDir.add(campoCidade);
        painelDir.add(campoBairro);
        painelDir.add(campoRua);
        painelDir.add(campoNumero);
        painelDir.add(campoCEP);
        painelDir.add(campoDataNascimento);
        painelDir.add(painelTipoSanguineoRh);  // Adicionando o painel de Tipo Sanguíneo e Fator Rh
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
