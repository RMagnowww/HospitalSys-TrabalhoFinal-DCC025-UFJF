package br.ufjf.dcc.model;
import br.ufjf.dcc.model.enums.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    private static final String ARQUIVO = "usuarios.txt";
    // ================= SALVAR USUÁRIOS=================
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
                    m.getTelefone() + ";" +
                    m.getEmail() + ";" +
                    m.getSenha() + ";" +
                    m.getCrm() + ";" +
                    m.getEspecialidade()  + ";" +
                    m.getAtividade() + ";" +
                    (m.getHorarioInicioExpediente() == null ? "" : m.getHorarioInicioExpediente()) + ";" +
                    (m.getHorarioFimExpediente() == null ? "" : m.getHorarioFimExpediente())
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

            bw.newLine();
        }
    }

    // ================= CARREGAR USUARIOS =================
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
                        if(d[8].equals("ATIVO"))
                            m.setAtividade(StatusMedico.ATIVO);
                        else if(d[8].equals("INATIVO"))
                                m.setAtividade(StatusMedico.INATIVO); 
                        if (d.length > 9) m.setHorarioInicioExpediente(d[9]);
                        if (d.length > 10) m.setHorarioFimExpediente(d[10]);
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

    // ================= CARREGAR PACIENTES =================
    public static ArrayList<Paciente> carregarPacientes() throws IOException {
        ArrayList<Paciente> lista = new ArrayList<>();
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
                }
            }
        }
        return lista;
    }

    // ================= CARREGAR MEDICOS =================
    public static ArrayList<Medico> carregarMedicos() throws IOException {
        ArrayList<Medico> lista = new ArrayList<>();
        File f = new File(ARQUIVO);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");

                switch (d[0]) {

                    case "MEDICO" -> {
                        Medico m = new Medico(
                            d[1], d[2], d[3], d[4], d[5],
                            d[6], d[7]
                        );
                        if(d[8].equals("ATIVO"))
                            m.setAtividade(StatusMedico.ATIVO);
                        else if(d[8].equals("INATIVO"))
                                m.setAtividade(StatusMedico.INATIVO); 
                        if (d.length > 9) m.setHorarioInicioExpediente(d[9]);
                        if (d.length > 10) m.setHorarioFimExpediente(d[10]);
                        lista.add(m);
                    }
                }
            }
        }
        return lista;
    }

    // ================= DELETAR USUÁRIO =================
    public static void deletarUsuario(Usuario u) throws IOException {
        String linhaExcluir = new String();
            if (u instanceof Paciente p) {
                Endereco e = p.getEndereco();
                linhaExcluir = (
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
                    p.isAceitaVisitas());
            }

            else if (u instanceof Medico m) {
                linhaExcluir = (
                    "MEDICO;" +
                    m.getNome() + ";" +
                    m.getCpf() + ";" +
                    m.getTelefone() + ";" +
                    m.getEmail() + ";" +
                    m.getSenha() + ";" +
                    m.getCrm() + ";" +
                    m.getEspecialidade()  + ";" +
                    m.getAtividade() + ";" +
                    (m.getHorarioInicioExpediente() == null ? "" : m.getHorarioInicioExpediente()) + ";" +
                    (m.getHorarioFimExpediente() == null ? "" : m.getHorarioFimExpediente())
                );
            }

            else if (u instanceof Secretario s) {
                linhaExcluir = (
                    "SECRETARIO;" +
                    s.getNome() + ";" +
                    s.getCpf() + ";" +
                    s.getEmail() + ";" +
                    s.getTelefone() + ";" +
                    s.getSenha() + ";" +
                    s.getMatricula()
                );
            }
        String arquivoTemporario = "usuarios_temp.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));
             BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTemporario))) {

            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.equals(linhaExcluir)) {
                    bw.write(linha);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path originalPath = Paths.get(ARQUIVO);
        Path tempPath = Paths.get(arquivoTemporario);

        try {
            Files.delete(originalPath);
            Files.move(tempPath, originalPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 private static final String ARQUIVOCONSULTAS = "consultas.txt";
    // ================= SALVAR CONSULTAS =================
    public static void salvarConsultas(Consulta c) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVOCONSULTAS, true))) {
                bw.write(
                    c.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ";" +
                    c.getPaciente().getNome() + ";" +
                    c.getPaciente().getCpf() + ";" +
                    c.getMedico().getNome() + ";" +
                    c.getMedico().getCpf() + ";" +
                    c.getStatus() + ";" +
                    c.getDescricao()

                    
                );
                bw.newLine();
            
        }
    }
     // ================= CARREGAR CONSULTAS DO PACIENTE REALIZADAS =================
    public static ArrayList<Consulta> carregarConsultasPacienteRealizadas(Paciente p) throws IOException {
        ArrayList<Consulta> lista = new ArrayList<>();
        File f = new File(ARQUIVOCONSULTAS);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");

                switch (d[5]) {

                    case "REALIZADA" -> {
                        ArrayList<Medico> medicos = carregarMedicos();
                        for (Medico m : medicos) {
                            if (p.getNome().equals(d[1]) && p.getCpf().equals(d[2]) && m.getNome().equals(d[3]) && m.getCpf().equals(d[4])) {
                               Consulta c = new Consulta(LocalDateTime.parse(d[0], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), p, m, StatusConsulta.REALIZADA);
                               c.setDescricao(d[6]);
                               lista.add(c);  
                            }
                        }
       
                    }
                }
            }
        }
        return lista;
    }
     // ================= CARREGAR CONSULTAS DO PACIENTE AGENDADAS =================
    public static ArrayList<Consulta> carregarConsultasPacienteAgendadas(Paciente p) throws IOException {
        ArrayList<Consulta> lista = new ArrayList<>();
        File f = new File(ARQUIVOCONSULTAS);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");

                switch (d[5]) {

                    case "AGENDADA" -> {
                        ArrayList<Medico> medicos = carregarMedicos();
                        for (Medico m : medicos) {
                            if (p.getNome().equals(d[1]) && p.getCpf().equals(d[2]) && m.getNome().equals(d[3]) && m.getCpf().equals(d[4])) {
                               Consulta c = new Consulta(LocalDateTime.parse(d[0], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), p, m, StatusConsulta.AGENDADA);
                                c.setDescricao(d[6]);
                               lista.add(c);  
                            }
                        }
       
                    }
                }
            }
        }
        return lista;
    }
     // ================= CARREGAR CONSULTAS DO MÉDICO AGENDADAS =================
    public static ArrayList<Consulta> carregarConsultasMedicoAgendadas(Medico m) throws IOException {
        ArrayList<Consulta> lista = new ArrayList<>();
        File f = new File(ARQUIVOCONSULTAS);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");

                switch (d[5]) {

                    case "AGENDADA" -> {
                        ArrayList<Paciente> pacientes = carregarPacientes();
                        for (Paciente p : pacientes) {
                            if (p.getNome().equals(d[1]) && p.getCpf().equals(d[2]) && m.getNome().equals(d[3]) && m.getCpf().equals(d[4])) {
                               Consulta c = new Consulta(LocalDateTime.parse(d[0], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), p, m, StatusConsulta.AGENDADA);
                                c.setDescricao(d[6]); 
                               lista.add(c);  
                            }
                        }
       
                    }
                }
            }
        }
        return lista;
    }
     // ================= CARREGAR CONSULTAS COM FALTA =================
    public static ArrayList<Consulta> carregarConsultasFalta() throws IOException {
        ArrayList<Consulta> lista = new ArrayList<>();
        File f = new File(ARQUIVOCONSULTAS);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");

                switch (d[5]) {

                    case "FALTA" -> {
                        ArrayList<Paciente> pacientes = carregarPacientes();
                        ArrayList<Medico> medicos = carregarMedicos();
                        for (Paciente p : pacientes)
                            for(Medico m : medicos) {
                                if (p.getNome().equals(d[1]) && p.getCpf().equals(d[2]) && m.getNome().equals(d[3]) && m.getCpf().equals(d[4])) {
                                    Consulta c = new Consulta(LocalDateTime.parse(d[0], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), p, m, StatusConsulta.FALTA);
                                    c.setDescricao(d[6]); 
                                    lista.add(c);  
                                }
                            }
       
                    }
                }
            }
        }
        return lista;
    }
    // ================= DELETAR CONSULTA =================
    public static void deletarConsulta(Consulta c) throws IOException {
        String linhaExcluir = new String();
                linhaExcluir = (
                    c.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ";" +
                    c.getPaciente().getNome() + ";" +
                    c.getPaciente().getCpf() + ";" +
                    c.getMedico().getNome() + ";" +
                    c.getMedico().getCpf() + ";" +
                    c.getStatus() + ";" +
                    c.getDescricao());

        String arquivoTemporarioConsultas = "consultas_temp.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVOCONSULTAS));
             BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTemporarioConsultas))) {

            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.equals(linhaExcluir)) {
                    bw.write(linha);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path originalPath = Paths.get(ARQUIVOCONSULTAS);
        Path tempPath = Paths.get(arquivoTemporarioConsultas);

        try {
            Files.delete(originalPath);
            Files.move(tempPath, originalPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String ARQUIVODOCUMENTOS = "documentos.txt";
     // ================= SALVAR DOCUMENTOS =================
    public static void salvarDocumentos(DocumentoMedico d) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVODOCUMENTOS, true))) {
               if (d instanceof Atestado a) {
                bw.write(
                    "ATESTADO;" +
                    a.getMedico().getNome() + ";" +
                    a.getMedico().getCpf() + ";" +
                    a.getPaciente().getNome() + ";" +
                    a.getPaciente().getCpf() + ";" +
                    a.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ";" +
                    a.getDiasAfastamento() + ";" +
                    a.getDescricao()
                );
            }

            else if (d instanceof Exame e) {
                bw.write(
                    "EXAME;" +
                    e.getMedico().getNome() + ";" +
                    e.getMedico().getCpf() + ";" +
                    e.getPaciente().getNome() + ";" +
                    e.getPaciente().getCpf() + ";" +
                    e.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ";" +
                    e.getNomeExame()  + ";" +
                    e.getResultado()
                );
            }

            else if (d instanceof Receita r) {
                bw.write(
                    "RECEITA;" +
                    r.getMedico().getNome() + ";" +
                    r.getMedico().getCpf() + ";" +
                    r.getPaciente().getNome() + ";" +
                    r.getPaciente().getCpf() + ";" +
                    r.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ";" +
                    r.getMedicamentos()
                );
            }

            bw.newLine();
        }
    }
        // ================= CARREGAR DOCUMENTOS DO PACIENTE =================
    public static List<DocumentoMedico> carregarDocumentosPaciente(Paciente p) throws IOException {
        List<DocumentoMedico> lista = new ArrayList<>();
        ArrayList<Medico> medicos = carregarMedicos();
        File f = new File(ARQUIVODOCUMENTOS);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");

                switch (d[0]) {

                    case "ATESTADO" -> {
                        for(Medico m : medicos)
                            if (m.getNome().equals(d[1]) && m.getCpf().equals(d[2]) && p.getNome().equals(d[3]) && p.getCpf().equals(d[4])) {
                                Atestado a = new Atestado(m, p, Integer.parseInt(d[6]), d[7]);
                                a.setData(LocalDateTime.parse(d[5], DateTimeFormatter.ofPattern("dd/MM/yyyy"))); 
                                lista.add(a);
                            }
                    }

                    case "EXAME" -> {
                        for(Medico m : medicos)
                            if (m.getNome().equals(d[1]) && m.getCpf().equals(d[2]) && p.getNome().equals(d[3]) && p.getCpf().equals(d[4])) {
                                Exame e = new Exame(m, p, d[6], d[7]);
                                e.setData(LocalDateTime.parse(d[5], DateTimeFormatter.ofPattern("dd/MM/yyyy"))); 
                                lista.add(e);
                            }
                    }

                    case "RECEITA" -> {
                        for(Medico m : medicos)
                            if (m.getNome().equals(d[1]) && m.getCpf().equals(d[2]) && p.getNome().equals(d[3]) && p.getCpf().equals(d[4])) {
                                Receita r = new Receita(m, p, d[6]);
                                r.setData(LocalDateTime.parse(d[5], DateTimeFormatter.ofPattern("dd/MM/yyyy"))); 
                                lista.add(r);
                    }
                }
            }
        }
        return lista;
        }
    }
}



