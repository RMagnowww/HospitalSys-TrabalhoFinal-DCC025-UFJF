package br.ufjf.dcc.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    private static final String ARQUIVO = "usuarios.txt";

    // ================= SALVAR =================
    public static void salvarUsuario(Usuario u) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {

            if (u instanceof Paciente p) {
                Endereco e = p.getEndereco();
                bw.write(
                    "PACIENTE;" +
                    p.getNome() + ";" +
                    p.getCpf() + ";" +
                    p.getTelefone() + ";" +
                    p.getEmail() + ";" +
                    p.getSenha() + ";" +
                    e.getRua() + ";" +
                    e.getBairro() + ";" +
                    e.getCep() + ";" +
                    e.getCidade() + ";" +
                    e.getNumero() + ";" +
                    p.getDataNascimento() + ";" +
                    p.getTipoSanguineo() + ";" +
                    p.isAceitaVisitas()
                );
            }

            else if (u instanceof Medico m) {
                bw.write(
                    "MEDICO;" +
                    m.getNome() + ";" +
                    m.getCpf() + ";" +
                    m.getEmail() + ";" +
                    m.getTelefone() + ";" +
                    m.getSenha() + ";" +
                    m.getCrm() + ";" +
                    m.getEspecialidade()
                );
            }

            else if (u instanceof Secretario s) {
                bw.write(
                    "SECRETARIO;" +
                    s.getNome() + ";" +
                    s.getCpf() + ";" +
                    s.getEmail() + ";" +
                    s.getTelefone() + ";" +
                    s.getSenha() + ";" +
                    s.getMatricula()
                );
            }

            bw.newLine(); // 🔴 ISSO É O QUE FAZ SALVAR VÁRIOS USUÁRIOS
        }
    }

    // ================= CARREGAR =================
    public static List<Usuario> carregarUsuarios() throws IOException {
        List<Usuario> lista = new ArrayList<>();
        File f = new File(ARQUIVO);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");

                switch (d[0]) {

                    case "PACIENTE" -> {
                        Endereco e = new Endereco(d[6], d[7], d[8], d[9], d[10]);
                        Paciente p = new Paciente(
                            d[1], d[2], d[3], d[4], d[5],
                            e, d[11], d[12]
                        );
                        p.setAceitaVisitas(Boolean.parseBoolean(d[13]));
                        lista.add(p);
                    }

                    case "MEDICO" -> {
                        Medico m = new Medico(
                            d[1], d[2], d[3], d[4], d[5],
                            d[6], d[7]
                        );
                        lista.add(m);
                    }

                    case "SECRETARIO" -> {
                        Secretario s = new Secretario(
                            d[1], d[2], d[3], d[4], d[5], d[6]
                        );
                        lista.add(s);
                    }
                }
            }
        }
        return lista;
    }
}
