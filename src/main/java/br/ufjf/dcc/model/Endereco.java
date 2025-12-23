package br.ufjf.dcc.model;

public class Endereco {
    private String rua;
    private String bairro;
    private String cep;
    private String cidade;
    private String numero;
    
    public Endereco(String rua, String bairro, String cep, String cidade, String numero) {
        this.rua = rua;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.numero = numero;
    }
}
