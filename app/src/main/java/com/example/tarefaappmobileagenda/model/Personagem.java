package com.example.tarefaappmobileagenda.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {
// constructor feito para chamar as variaveis.

    private String nome;
    private String altura;
    private String nascimento;
    private String telefone;
    private String endereco;
    private String cep;
    private String rg;
    private String genero;
    private int id = 0;

    public Personagem(String nome, String altura, String nascimento, String telefone, String endereco, String cep, String rg, String genero   ) { //variaveis usadas no aplicativo.
        // indica elas
        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cep = cep;
        this.rg = rg;
        this.genero = genero;
    }

    public Personagem() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    // converte o nome para string para exibiçao.
    @NonNull
    @Override
    public String toString() {
        return nome;
    }       //retorna a string nome para o aplicativo.

    public void setId(int id) {
        this.id = id;
    }       //posicionamento na localizaçao da lista.

    public int getId() {
        return id;
    }

    {

    }

    public boolean IdValido() {

        return  id > 0;
    }

    //public boolean IdValido(){ return id> 0;

}







