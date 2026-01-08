/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */
    
package br.ufjf.dcc.view.TelasSecretario;
import javax.swing.*;
import br.ufjf.dcc.model.Secretario;
import java.awt.*;

public class TelaMenuSecretario {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JButton botaoCadastroPaciente;
    private JButton botaoVisitasInfo;
    private JButton botaoCorpoClinico;
    private JButton botaoDisponibilidade;
    private JButton botaoSair;

    public TelaMenuSecretario() {
        frame = new JFrame("Menu - Secretário");
        painelPrincipal = new JPanel();
        painelEsq = new JPanel(); 
        painelDir = new JPanel();  
        botaoCadastroPaciente = new JButton("Cadastro de Pacientes");
        botaoVisitasInfo = new JButton("Internações e Visitas");
        botaoCorpoClinico = new JButton("Corpo Clínico");
        botaoDisponibilidade = new JButton("Disponibilidade");
        botaoSair = new JButton("Sair");
    }

    public void abrirTelaMenuSecretario(Secretario secretario){
        frame.setSize(800,450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        botaoCadastroPaciente.addActionListener(e -> {
            TelaCadastroPaciente telaCadastroPaciente = new TelaCadastroPaciente();
            telaCadastroPaciente.abrirTelaCadastroPaciente();
        });
        botaoVisitasInfo.addActionListener(e -> {
            TelaInternadosVisitas telaInternadosVisitas = new TelaInternadosVisitas();
            telaInternadosVisitas.abrirTelaInternadosVisitas();
        });
        botaoCorpoClinico.addActionListener( e-> {
            TelaCorpoClinico telaCorpoClinico = new TelaCorpoClinico();
            telaCorpoClinico.abrirTelaCorpoClinico();
        });
        botaoDisponibilidade.addActionListener( e->{
            TelaDisponibilidadeMedicos telaDisponibilidadeMedicos = new TelaDisponibilidadeMedicos();
            telaDisponibilidadeMedicos.abrirTelaDisponibilidadeMedicos();
        });
        botaoSair.addActionListener(e -> frame.dispose());

        painelPrincipal.setLayout(new GridLayout(0, 2, 25, 0));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(45,35,45,35));

        painelEsq.setLayout(new GridLayout(0,1,0,25));
        painelEsq.add(botaoCadastroPaciente);
        painelEsq.add(botaoVisitasInfo);
        painelEsq.add(botaoCorpoClinico);

        painelDir.setLayout(new GridLayout(0,1,0,25));
        painelDir.setBorder(BorderFactory.createEmptyBorder(0,0,115,0));
        painelDir.add(botaoDisponibilidade);
        painelDir.add(botaoSair);

        painelPrincipal.add(painelEsq);
        painelPrincipal.add(painelDir);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
