/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */
    
package br.ufjf.dcc.view.TelasMedico;
import javax.swing.*;

import br.ufjf.dcc.model.enums.TipoDocumento;
import br.ufjf.dcc.model.Persistencia;
import br.ufjf.dcc.model.Paciente;
import br.ufjf.dcc.model.Medico;
import br.ufjf.dcc.model.Atestado;
import br.ufjf.dcc.model.Exame;
import br.ufjf.dcc.model.Receita;
import java.util.ArrayList;

import java.awt.*;
import java.io.IOException;

public class TelaEmitirDocs {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelDir;
    private JPanel painelBotoes;
    private JPanel painelInfo;
    private JPanel painelLabel;
    private JPanel painelCampo;
    private JPanel painelBox;
    private JTextPane campoDocumento;
    private JTextField campoPaciente;
    private JTextField campoPacienteCpf;
    private JTextField campoNomeExame;
    private JTextField campoDiasAtestado;
    private JTextPane paneResultadoExame;
    private JTextPane paneDescricaoAtestado;
    private JTextPane paneMedicamentosReceita;
    private JComboBox<TipoDocumento> boxTipo;
    private JLabel labelPaciente;
     private JLabel labelPacienteCpf;
    private JLabel labelNomeExame;
    private JLabel labelDiasAtestado;
    private JLabel labelTipo;
    private JButton botaoSair;
    private JButton botaoEmitir;

    public TelaEmitirDocs(){
        frame = new JFrame("Emitir Documentos");
        painelPrincipal = new JPanel();
        painelDir = new JPanel();
        painelBotoes = new JPanel();
        painelInfo = new JPanel();
        painelLabel = new JPanel();
        painelCampo = new JPanel();
        painelBox = new JPanel();
        campoDocumento = new JTextPane();
        campoPaciente = new JTextField(23);
        campoPacienteCpf = new JTextField(23);
        campoNomeExame = new JTextField(23);
        campoDiasAtestado = new JTextField(23);
        paneResultadoExame = new JTextPane();
        paneDescricaoAtestado = new JTextPane();
        paneMedicamentosReceita = new JTextPane();
        boxTipo = new JComboBox<TipoDocumento>(TipoDocumento.values());
        boxTipo.setSelectedItem(null);
        labelPaciente = new JLabel("Paciente:");
        labelPacienteCpf = new JLabel("CPF:");
        labelNomeExame = new JLabel("Nome do Exame:");
        labelDiasAtestado = new JLabel("Dias de Afastamento:");
        labelTipo = new JLabel("Tipo de Documento:");
        botaoSair = new JButton("Sair");
        botaoEmitir = new JButton("Emitir Documento");
    }
    
    public void abrirTelaEmitirDocs(Medico medico){
        frame.setSize(800,450);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        botaoEmitir.addActionListener(e ->{
            if(boxTipo.getSelectedItem() != null)
                try{
                    ArrayList<Paciente> pacientes = Persistencia.carregarPacientes();
                    if(boxTipo.getSelectedItem() == TipoDocumento.ATESTADO){
                        Atestado atestado = null;
                        for(Paciente p : pacientes)
                            if(p.getNome().equals(campoPaciente.getText()) && p.getCpf().equals(campoPacienteCpf.getText()))
                                atestado = new Atestado(medico, p, Integer.parseInt(campoDiasAtestado.getText()), paneDescricaoAtestado.getText());
                            if(atestado != null){
                                campoDocumento.setText(atestado.gerarConteudo());
                                Persistencia.salvarDocumentos(atestado);
                                JOptionPane.showMessageDialog(new JFrame(),"Atestado Emitido com Sucesso!","SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else
                               JOptionPane.showMessageDialog(new JFrame(),"Paciente não Encontrado!","ERRO", JOptionPane.ERROR_MESSAGE);
                        }
                    if(boxTipo.getSelectedItem() == TipoDocumento.EXAME){
                        Exame exame = null;
                        for(Paciente p : pacientes)
                            if(p.getNome().equals(campoPaciente.getText()) && p.getCpf().equals(campoPacienteCpf.getText()))
                                exame = new Exame(medico, p,campoNomeExame.getText(), paneResultadoExame.getText());
                            if(exame != null){
                                campoDocumento.setText(exame.gerarConteudo());
                                Persistencia.salvarDocumentos(exame);
                                JOptionPane.showMessageDialog(new JFrame(),"Exame Emitido com Sucesso!","SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else
                               JOptionPane.showMessageDialog(new JFrame(),"Paciente não Encontrado!","ERRO", JOptionPane.ERROR_MESSAGE);
                    }   
                    if(boxTipo.getSelectedItem() == TipoDocumento.RECEITA){
                        Receita receita = null;
                        for(Paciente p : pacientes)
                            if(p.getNome().equals(campoPaciente.getText()) && p.getCpf().equals(campoPacienteCpf.getText()))
                                receita = new Receita(medico, p, paneMedicamentosReceita.getText());
                            if(receita != null){
                                campoDocumento.setText(receita.gerarConteudo());
                                Persistencia.salvarDocumentos(receita);
                                JOptionPane.showMessageDialog(new JFrame(),"Receita Emitida com Sucesso!","SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else
                               JOptionPane.showMessageDialog(new JFrame(),"Paciente não Encontrado!","ERRO", JOptionPane.ERROR_MESSAGE);
                
                    }
                } 
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            else
                JOptionPane.showMessageDialog(new JFrame(),"Selecione um tipo de documento!","ERRO", JOptionPane.ERROR_MESSAGE);
            
        });
        botaoSair.addActionListener(e -> frame.dispose());

        paneResultadoExame.setBorder(BorderFactory.createTitledBorder("Descrição:"));
        paneResultadoExame.setPreferredSize(new Dimension(300,200));
        paneDescricaoAtestado.setBorder(BorderFactory.createTitledBorder("Descrição:"));
        paneDescricaoAtestado.setPreferredSize(new Dimension(300,200));
        paneMedicamentosReceita.setBorder(BorderFactory.createTitledBorder("Medicamentos:"));
        paneMedicamentosReceita.setPreferredSize(new Dimension(300,200));

        boxTipo.addActionListener(e -> {
            painelInfo.setVisible(false);
            painelInfo.removeAll();
            painelLabel.removeAll();
            painelCampo.removeAll();
            if(boxTipo.getSelectedItem() == TipoDocumento.ATESTADO){
                campoDocumento.setText(null);
                campoPaciente.setText(null);
                campoPacienteCpf.setText(null);
                campoDiasAtestado.setText(null);
                paneDescricaoAtestado.setText(null);
                painelLabel.setLayout(new GridLayout(3,0,0,10));
                    painelLabel.add(labelPaciente);
                    painelLabel.add(labelPacienteCpf);
                    painelLabel.add(labelDiasAtestado);
                painelCampo.setLayout(new GridLayout(3,0,0,10));
                    painelCampo.add(campoPaciente);
                    painelCampo.add(campoPacienteCpf);
                    painelCampo.add(campoDiasAtestado);
                painelInfo.add(painelLabel, BorderLayout.WEST);
                painelInfo.add(painelCampo, BorderLayout.CENTER);
                painelInfo.add(new JScrollPane (paneDescricaoAtestado), BorderLayout.SOUTH);
                painelInfo.setVisible(true);
            }
            if(boxTipo.getSelectedItem() == TipoDocumento.EXAME){
                campoDocumento.setText(null);
                campoPaciente.setText(null);
                campoPacienteCpf.setText(null);
                campoNomeExame.setText(null);
                paneResultadoExame.setText(null);
                painelLabel.setLayout(new GridLayout(3,0,0,10));
                    painelLabel.add(labelPaciente);
                    painelLabel.add(labelPacienteCpf);
                    painelLabel.add(labelNomeExame);
                painelCampo.setLayout(new GridLayout(3,0,0,10));
                    painelCampo.add(campoPaciente);
                    painelCampo.add(campoPacienteCpf);
                    painelCampo.add(campoNomeExame);
                painelInfo.add(painelLabel, BorderLayout.WEST);
                painelInfo.add(painelCampo, BorderLayout.CENTER);
                painelInfo.add(new JScrollPane (paneResultadoExame), BorderLayout.SOUTH);
                painelInfo.setVisible(true);
            }   
            if(boxTipo.getSelectedItem() == TipoDocumento.RECEITA){
                campoDocumento.setText(null);
                campoPaciente.setText(null);
                campoPacienteCpf.setText(null);
                paneMedicamentosReceita.setText(null);
                painelLabel.setLayout(new GridLayout(2,0,0,10));
                    painelLabel.add(labelPaciente);
                    painelLabel.add(labelPacienteCpf);
                painelCampo.setLayout(new GridLayout(2,0,0,10));
                    painelCampo.add(campoPaciente);
                    painelCampo.add(campoPacienteCpf);
                painelInfo.add(painelLabel, BorderLayout.WEST);
                painelInfo.add(painelCampo, BorderLayout.CENTER);
                painelInfo.add(new JScrollPane (paneMedicamentosReceita), BorderLayout.SOUTH);
                painelInfo.setVisible(true);
            }
        });

        campoDocumento.setBorder(BorderFactory.createTitledBorder("Documento"));
        campoDocumento.setPreferredSize(new Dimension(350,300));
        campoDocumento.setEditable(false);

        painelBotoes.setLayout(new GridLayout(0,2,10,0));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoEmitir);

        painelBox.setLayout(new BorderLayout(10,10));
        painelBox.add(labelTipo, BorderLayout.WEST);
        painelBox.add(boxTipo, BorderLayout.CENTER);

        painelInfo.setLayout(new BorderLayout(10,10));
        painelInfo.setBorder(BorderFactory.createTitledBorder("Informações para Emissão"));
        painelInfo.add(painelLabel, BorderLayout.WEST);
        painelInfo.add(painelCampo, BorderLayout.CENTER);

        painelDir.setLayout(new BorderLayout(10,10));
        painelDir.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        painelDir.add(painelBox, BorderLayout.NORTH);
        painelDir.add(painelInfo, BorderLayout.CENTER);

        painelPrincipal.setLayout(new BorderLayout(10,10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
        painelPrincipal.add(painelDir, BorderLayout.CENTER);
        painelPrincipal.add(new JScrollPane(campoDocumento), BorderLayout.EAST);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
