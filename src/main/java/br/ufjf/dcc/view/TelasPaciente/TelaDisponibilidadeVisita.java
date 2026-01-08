/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */
    
package br.ufjf.dcc.view.TelasPaciente;
import br.ufjf.dcc.controller.PacienteController;
import javax.swing.*;
import java.awt.*;

public class TelaDisponibilidadeVisita{
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelSuperior;
    private JPanel painelInferior;
    private JPanel painelSupEsq;
    private JPanel painelSupDir;
    private JTextField campoPaciente;
    private JLabel labelPaciente;
    private JLabel labelAptidao;
    private JLabel campoAptidao;
    private JButton botaoSair;
    private JButton botaoBuscar;


    public TelaDisponibilidadeVisita(){
        frame = new JFrame("Verificar Disponibilidade de Visita");
        painelPrincipal = new JPanel();
        painelSuperior = new JPanel();
        painelInferior = new JPanel();
        painelSupEsq = new JPanel();
        painelSupDir = new JPanel();
        campoPaciente = new JTextField();
        labelPaciente = new JLabel("Nome do Paciente:");
        labelAptidao = new JLabel("Aptidão a Visitas:");
        campoAptidao = new JLabel();
        botaoSair = new JButton("Sair");
        botaoBuscar = new JButton("Buscar");
    }
    public void abrirTelaDisponibilidadeVisita(){
        frame.setSize(400,180);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(5,5));

        botaoBuscar.addActionListener(e -> {
            String apt = PacienteController.checarDisponibilidadeVisita(campoPaciente.getText());
            campoAptidao.setText(apt);
            if(apt != null){
                if(apt.equals("APTO"))
                    campoAptidao.setForeground(new Color(0,155,0));
                else
                    campoAptidao.setForeground(new Color(155,35,35));
            }
        });
        botaoSair.addActionListener(e -> frame.dispose());

        painelSupEsq.setLayout(new GridLayout(2,0,0,15));
        painelSupEsq.add(labelPaciente);
        painelSupEsq.add(labelAptidao);

        painelSupDir.setLayout(new GridLayout(2,0,0,15));
        painelSupDir.add(campoPaciente);
        painelSupDir.add(campoAptidao);

        painelSuperior.setLayout(new BorderLayout(5,5));
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(15,0,10,0));
        painelSuperior.add(painelSupEsq, BorderLayout.WEST);
        painelSuperior.add(painelSupDir, BorderLayout.CENTER);

        painelInferior.setLayout(new GridLayout(0,2,10,0));
        painelInferior.setBorder(BorderFactory.createEmptyBorder(0,75,0,75));
        painelInferior.add(botaoSair);
        painelInferior.add(botaoBuscar);

        painelPrincipal.setLayout(new BorderLayout(5,5));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        painelPrincipal.add(painelSuperior, BorderLayout.CENTER);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        frame.add(painelPrincipal);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}