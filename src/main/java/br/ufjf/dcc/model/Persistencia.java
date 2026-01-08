/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */
    
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

    private static final String ARQUIVO = "data/usuarios.txt";
    // ================= SALVAR USUÁRIOS=================
    public static void salvarUsuario(Usuario u) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            if (u instanceof Paciente p) {
                Endereco e = p.getEndereco();
                bw.write(
                    "PACIENTE;" +
                    p.getNome().replaceAll(";","΋") + ";" +
                    p.getCpf().replaceAll(";","΋") + ";" +
                    p.getTelefone().replaceAll(";","΋") + ";" +
                    p.getEmail().replaceAll(";","΋") + ";" +
                    p.getSenha().replaceAll(";","΋") + ";" +
                    e.getRua().replaceAll(";","΋") + ";" +
                    e.getBairro().replaceAll(";","΋") + ";" +
                    e.getCep().replaceAll(";","΋") + ";" +
                    e.getCidade().replaceAll(";","΋") + ";" +
                    e.getNumero().replaceAll(";","΋") + ";" +
                    p.getDataNascimento().replaceAll(";","΋") + ";" +
                    p.getTipoSanguineo().replaceAll(";","΋") + ";" +
                    p.isAceitaVisitas()
                );
            }

            else if (u instanceof Medico m) {
                ArrayList<Boolean> b = m.getDiasTrabalha();
                ArrayList<Boolean> allFalse = new ArrayList<Boolean>(7);
                for(int i = 0; i < 7; i++)
                    allFalse.add(false);
                bw.write(
                    "MEDICO;" +
                    m.getNome().replaceAll(";","΋") + ";" +
                    m.getCpf().replaceAll(";","΋") + ";" +
                    m.getTelefone().replaceAll(";","΋") + ";" +
                    m.getEmail().replaceAll(";","΋") + ";" +
                    m.getSenha().replaceAll(";","΋") + ";" +
                    m.getCrm().replaceAll(";","΋") + ";" +
                    m.getEspecialidade().replaceAll(";","΋")  + ";" +
                    m.getAtividade() + ";" +
                    (m.getHorarioInicioExpediente() == null ? "" : m.getHorarioInicioExpediente()) + ";" +
                    (m.getHorarioFimExpediente() == null ? "" : m.getHorarioFimExpediente()) + ";" +
                    (m.getDuracaoConsulta() == 0 ? "" : m.getDuracaoConsulta()) + ";" +
                    (m.getDiasTrabalha().equals(allFalse) ? "" : b.get(0) + ";" + 
                    b.get(1) + ";" + 
                    b.get(2) + ";" + 
                    b.get(3) + ";" + 
                    b.get(4) + ";" + 
                    b.get(5) + ";" + 
                    b.get(6))
                );
            }

            else if (u instanceof Secretario s) {
                bw.write(
                    "SECRETARIO;" +
                    s.getNome().replaceAll(";","΋") + ";" +
                    s.getCpf().replaceAll(";","΋") + ";" +
                    s.getEmail().replaceAll(";","΋") + ";" +
                    s.getTelefone().replaceAll(";","΋") + ";" +
                    s.getSenha().replaceAll(";","΋") + ";" +
                    s.getMatricula().replaceAll(";","΋")
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
                        Endereco e = new Endereco(d[6].replaceAll("΋",";") , d[7].replaceAll("΋",";") , d[8].replaceAll("΋",";") , d[9].replaceAll("΋",";") , d[10].replaceAll("΋",";") );
                        Paciente p = new Paciente(
                            d[1].replaceAll("΋",";") , d[2].replaceAll("΋",";") , d[3].replaceAll("΋",";") , 
                            d[4].replaceAll("΋",";") , d[5].replaceAll("΋",";") , e, d[11].replaceAll("΋",";") ,
                            d[12].replaceAll("΋",";")
                        );
                        p.setAceitaVisitas(Boolean.parseBoolean(d[13]));
                        lista.add(p);
                    }

                    case "MEDICO" -> {
                        Medico m = new Medico(
                            d[1].replaceAll("΋",";") , d[2].replaceAll("΋",";") , d[3].replaceAll("΋",";") ,
                            d[4].replaceAll("΋",";") , d[5].replaceAll("΋",";") ,
                            d[6].replaceAll("΋",";") , d[7].replaceAll("΋",";") 
                        );
                        if(d[8].equals("ATIVO"))
                            m.setAtividade(StatusMedico.ATIVO);
                        else if(d[8].equals("INATIVO"))
                                m.setAtividade(StatusMedico.INATIVO); 
                        if (d.length > 9) m.setHorarioInicioExpediente(d[9]);
                        if (d.length > 10) m.setHorarioFimExpediente(d[10]);
                        if (d.length > 11) m.setDuracaoConsulta(Integer.parseInt(d[11]));
                        if (d.length > 12){ 
                            ArrayList<Boolean> b = new ArrayList<Boolean>(7);
                            for(int i = 0; i < 7; i++)
                                b.add(Boolean.parseBoolean(d[12+i]));
                            m.setDiasTrabalha(b);
                        }
                        lista.add(m);
                    }

                    case "SECRETARIO" -> {
                        Secretario s = new Secretario(
                            d[1].replaceAll("΋",";") , d[2].replaceAll("΋",";") , d[3].replaceAll("΋",";") , 
                            d[4].replaceAll("΋",";") , d[5].replaceAll("΋",";") , d[6].replaceAll("΋",";") 
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
                        Endereco e = new Endereco(d[6].replaceAll("΋",";") , d[7].replaceAll("΋",";") , d[8].replaceAll("΋",";") , d[9].replaceAll("΋",";") , d[10].replaceAll("΋",";") );
                        Paciente p = new Paciente(
                            d[1].replaceAll("΋",";") , d[2].replaceAll("΋",";") , d[3].replaceAll("΋",";") , 
                            d[4].replaceAll("΋",";") , d[5].replaceAll("΋",";") , e, d[11].replaceAll("΋",";") ,
                            d[12].replaceAll("΋",";")
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
                            d[1].replaceAll("΋",";") , d[2].replaceAll("΋",";") , d[3].replaceAll("΋",";") ,
                            d[4].replaceAll("΋",";") , d[5].replaceAll("΋",";") ,
                            d[6].replaceAll("΋",";") , d[7].replaceAll("΋",";") 
                        );
                        if(d[8].equals("ATIVO"))
                            m.setAtividade(StatusMedico.ATIVO);
                        else if(d[8].equals("INATIVO"))
                                m.setAtividade(StatusMedico.INATIVO); 
                        if (d.length > 9) m.setHorarioInicioExpediente(d[9]);
                        if (d.length > 10) m.setHorarioFimExpediente(d[10]);
                        if (d.length > 11) m.setDuracaoConsulta(Integer.parseInt(d[11]));
                        if (d.length > 12){ 
                            ArrayList<Boolean> b = new ArrayList<Boolean>(7);
                            for(int i = 0; i < 7; i++)
                                b.add(Boolean.parseBoolean(d[12+i]));
                            m.setDiasTrabalha(b);
                        }
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
                    p.getNome().replaceAll(";","΋") + ";" +
                    p.getCpf().replaceAll(";","΋") + ";" +
                    p.getTelefone().replaceAll(";","΋") + ";" +
                    p.getEmail().replaceAll(";","΋") + ";" +
                    p.getSenha().replaceAll(";","΋") + ";" +
                    e.getRua().replaceAll(";","΋") + ";" +
                    e.getBairro().replaceAll(";","΋") + ";" +
                    e.getCep().replaceAll(";","΋") + ";" +
                    e.getCidade().replaceAll(";","΋") + ";" +
                    e.getNumero().replaceAll(";","΋") + ";" +
                    p.getDataNascimento().replaceAll(";","΋") + ";" +
                    p.getTipoSanguineo().replaceAll(";","΋") + ";" +
                    p.isAceitaVisitas()
                );
            }

            else if (u instanceof Medico m) {
                ArrayList<Boolean> b = m.getDiasTrabalha();
                ArrayList<Boolean> allFalse = new ArrayList<Boolean>(7);
                for(int i = 0; i < 7; i++)
                    allFalse.add(false);
                linhaExcluir = (
                   "MEDICO;" +
                    m.getNome().replaceAll(";","΋") + ";" +
                    m.getCpf().replaceAll(";","΋") + ";" +
                    m.getTelefone().replaceAll(";","΋") + ";" +
                    m.getEmail().replaceAll(";","΋") + ";" +
                    m.getSenha().replaceAll(";","΋") + ";" +
                    m.getCrm().replaceAll(";","΋") + ";" +
                    m.getEspecialidade().replaceAll(";","΋")  + ";" +
                    m.getAtividade() + ";" +
                    (m.getHorarioInicioExpediente() == null ? "" : m.getHorarioInicioExpediente()) + ";" +
                    (m.getHorarioFimExpediente() == null ? "" : m.getHorarioFimExpediente()) + ";" +
                    (m.getDuracaoConsulta() == 0 ? "" : m.getDuracaoConsulta()) + ";" +
                    (m.getDiasTrabalha().equals(allFalse) ? "" : b.get(0) + ";" + 
                    b.get(1) + ";" + 
                    b.get(2) + ";" + 
                    b.get(3) + ";" + 
                    b.get(4) + ";" + 
                    b.get(5) + ";" + 
                    b.get(6))
                );
            }

            else if (u instanceof Secretario s) {
                linhaExcluir = (
                    "SECRETARIO;" +
                    s.getNome().replaceAll(";","΋") + ";" +
                    s.getCpf().replaceAll(";","΋") + ";" +
                    s.getEmail().replaceAll(";","΋") + ";" +
                    s.getTelefone().replaceAll(";","΋") + ";" +
                    s.getSenha().replaceAll(";","΋") + ";" +
                    s.getMatricula().replaceAll(";","΋")
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

 private static final String ARQUIVOCONSULTAS = "data/consultas.txt";
    // ================= SALVAR CONSULTAS =================
    public static void salvarConsultas(Consulta c) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVOCONSULTAS, true))) {
                bw.write(
                    c.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")).replaceAll(";","΋") + ";" +
                    c.getPaciente().getNome().replaceAll(";","΋") + ";" +
                    c.getPaciente().getCpf().replaceAll(";","΋") + ";" +
                    c.getMedico().getNome().replaceAll(";","΋") + ";" +
                    c.getMedico().getCpf().replaceAll(";","΋") + ";" +
                    c.getStatus() + ";" +
                    c.getDescricao().replaceAll(";","΋")

                    
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
                            if (p.getNome().equals(d[1].replaceAll("΋",";")) && p.getCpf().equals(d[2].replaceAll("΋",";")) && m.getNome().equals(d[3].replaceAll("΋",";")) && m.getCpf().equals(d[4].replaceAll("΋",";"))) {
                               Consulta c = new Consulta(LocalDateTime.parse(d[0], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), p, m, StatusConsulta.REALIZADA);
                               c.setDescricao(d[6].replaceAll("΋",";"));
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
                            if (p.getNome().equals(d[1].replaceAll("΋",";")) && p.getCpf().equals(d[2].replaceAll("΋",";")) && m.getNome().equals(d[3].replaceAll("΋",";")) && m.getCpf().equals(d[4].replaceAll("΋",";"))) {
                               Consulta c = new Consulta(LocalDateTime.parse(d[0], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), p, m, StatusConsulta.AGENDADA);
                                c.setDescricao(d[6].replaceAll("΋",";"));
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
                            if (p.getNome().equals(d[1].replaceAll("΋",";")) && p.getCpf().equals(d[2].replaceAll("΋",";")) && m.getNome().equals(d[3].replaceAll("΋",";")) && m.getCpf().equals(d[4].replaceAll("΋",";"))) {
                               Consulta c = new Consulta(LocalDateTime.parse(d[0], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), p, m, StatusConsulta.AGENDADA);
                                c.setDescricao(d[6].replaceAll("΋",";")); 
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
                                if (p.getNome().equals(d[1].replaceAll("΋",";")) && p.getCpf().equals(d[2].replaceAll("΋",";")) && m.getNome().equals(d[3].replaceAll("΋",";")) && m.getCpf().equals(d[4].replaceAll("΋",";"))) {
                                    Consulta c = new Consulta(LocalDateTime.parse(d[0], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), p, m, StatusConsulta.FALTA);
                                    c.setDescricao(d[6].replaceAll("΋",";")); 
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
                    c.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")).replaceAll(";","΋") + ";" +
                    c.getPaciente().getNome().replaceAll(";","΋") + ";" +
                    c.getPaciente().getCpf().replaceAll(";","΋") + ";" +
                    c.getMedico().getNome().replaceAll(";","΋") + ";" +
                    c.getMedico().getCpf().replaceAll(";","΋") + ";" +
                    c.getStatus() + ";" +
                    c.getDescricao().replaceAll(";","΋"));

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

    private static final String ARQUIVODOCUMENTOS = "data/documentos.txt";
     // ================= SALVAR DOCUMENTOS =================
    public static void salvarDocumentos(DocumentoMedico d) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVODOCUMENTOS, true))) {
               if (d instanceof Atestado a) {
                bw.write(
                    "ATESTADO;" +
                    a.getMedico().getNome().replaceAll(";","΋")  + ";" +
                    a.getMedico().getCpf().replaceAll(";","΋")  + ";" +
                    a.getPaciente().getNome().replaceAll(";","΋")  + ";" +
                    a.getPaciente().getCpf().replaceAll(";","΋")  + ";" +
                    a.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ";" +
                    a.getDiasAfastamento() + ";" +
                    a.getDescricao().replaceAll(";","΋")
                );
            }

            else if (d instanceof Exame e) {
                bw.write(
                    "EXAME;" +
                    e.getMedico().getNome().replaceAll(";","΋")  + ";" +
                    e.getMedico().getCpf().replaceAll(";","΋")  + ";" +
                    e.getPaciente().getNome().replaceAll(";","΋")  + ";" +
                    e.getPaciente().getCpf().replaceAll(";","΋")  + ";" +
                    e.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ";" +
                    e.getNomeExame().replaceAll(";","΋")  + ";" +
                    e.getResultado().replaceAll(";","΋")
                );
            }

            else if (d instanceof Receita r) {
                bw.write(
                    "RECEITA;" +
                    r.getMedico().getNome().replaceAll(";","΋")  + ";" +
                    r.getMedico().getCpf().replaceAll(";","΋")  + ";" +
                    r.getPaciente().getNome().replaceAll(";","΋")  + ";" +
                    r.getPaciente().getCpf().replaceAll(";","΋")  + ";" +
                    r.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ";" +
                    r.getMedicamentos().replaceAll(";","΋")
                );
            }

            bw.newLine();
        }
    }
        // ================= CARREGAR DOCUMENTOS DO PACIENTE =================
    public static ArrayList<DocumentoMedico> carregarDocumentosPaciente(Paciente p) throws IOException {
        ArrayList<DocumentoMedico> lista = new ArrayList<>();
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
                            if (m.getNome().equals(d[1].replaceAll("΋",";")) && m.getCpf().equals(d[2].replaceAll("΋",";")) && p.getNome().equals(d[3].replaceAll("΋",";")) && p.getCpf().equals(d[4].replaceAll("΋",";"))) {
                                Atestado a = new Atestado(m, p, Integer.parseInt(d[6]), d[7].replaceAll("΋",";"));
                                a.setData(LocalDateTime.parse(d[5], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))); 
                                lista.add(a);
                            }
                    }

                    case "EXAME" -> {
                        for(Medico m : medicos)
                            if (m.getNome().equals(d[1].replaceAll("΋",";")) && m.getCpf().equals(d[2].replaceAll("΋",";")) && p.getNome().equals(d[3].replaceAll("΋",";")) && p.getCpf().equals(d[4].replaceAll("΋",";"))) {
                                Exame e = new Exame(m, p, d[6].replaceAll("΋",";"), d[7].replaceAll("΋",";"));
                                e.setData(LocalDateTime.parse(d[5], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))); 
                                lista.add(e);
                            }
                    }

                    case "RECEITA" -> {
                        for(Medico m : medicos)
                            if (m.getNome().equals(d[1].replaceAll("΋",";")) && m.getCpf().equals(d[2].replaceAll("΋",";")) && p.getNome().equals(d[3].replaceAll("΋",";")) && p.getCpf().equals(d[4].replaceAll("΋",";"))) {
                                Receita r = new Receita(m, p, d[6].replaceAll("΋",";"));
                                r.setData(LocalDateTime.parse(d[5], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))); 
                                lista.add(r);
                    }
                }
            }
        }
        return lista;
        }
    }
}



