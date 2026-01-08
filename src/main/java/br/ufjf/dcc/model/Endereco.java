/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */
    
package br.ufjf.dcc.model;

import javax.swing.JOptionPane;

public class Endereco {
    private String rua;
    private String bairro;
    private String cep;
    private String cidade;
    private String numero;
    
    public Endereco(String rua, String bairro, String cep, String cidade, String numero) {
        setRua(rua);
        setBairro(bairro);
        setCep(cep);
        setCidade(cidade);
        setNumero(numero);
    }

    public String getRua() { return rua; }
    public void setRua(String rua) {
        if(rua == null || rua.trim().isEmpty()) {
            throw new IllegalArgumentException("A rua não pode ser vazia");
        }
        this.rua = rua;
    }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) {
        if(bairro == null || bairro.trim().isEmpty()) {
            throw new IllegalArgumentException("O bairro não pode ser vazio");
        }
        this.bairro = bairro;
    }

    public String getCep() { return cep; }
    public void setCep(String cep) {
    try {
        if (cep == null || cep.trim().isEmpty()) {
            throw new IllegalArgumentException("CEP não pode ser vazio ou nulo.");
        }

        // Remove tudo que não for número
        cep = cep.replaceAll("[^0-9]", "");

        // Verifica se tem 8 dígitos
        if (cep.length() != 8 || !cep.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP deve conter 8 dígitos numéricos.");
        }

        // Formata o CEP: xxxxx-xxx
        String cepFormatado = String.format("%s-%s", 
            cep.substring(0, 5),   // primeiros 5 dígitos
            cep.substring(5));     // últimos 3 dígitos

        this.cep = cepFormatado;

    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "CEP Inválido", JOptionPane.ERROR_MESSAGE);
    }
}

    public String getCidade() { return cidade;}
    public void setCidade(String cidade) {
        if (cidade == null || cidade.trim().isEmpty()) {
            throw new IllegalArgumentException("A cidade não pode ser vazia");
        }
        this.cidade = cidade;
    }

    public String getNumero() { return numero;}
    public void setNumero(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("O número não pode ser vazio");
        }
        this.numero = numero;
    }

    @Override
    public String toString() {
        return rua + ", " + numero + " - " + bairro + ", " + cidade + " - CEP: " + cep;
    }
}



