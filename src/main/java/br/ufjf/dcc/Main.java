package br.ufjf.dcc;
import br.ufjf.dcc.view.TelaLogin;

public class Main{
    public static void main(String[] args){
        List<Usuario> usuarios = new ArrayList<>();

        usuarios.add(new Secretario("Admin", "000", "admin", "9999", "admin@hospital.com"));

        usuarios.add(new Medico("Dr João", "111", "123", "8888", "joao@hospital.com"));

        usuarios.add(new Paciente("Maria", "222", "123", "7777", "maria@gmail.com"));

        Persistencia.salvarUsuarios(usuarios);



        TelaLogin telaLogin = new TelaLogin();
        telaLogin.abrirLogin();
    }
}