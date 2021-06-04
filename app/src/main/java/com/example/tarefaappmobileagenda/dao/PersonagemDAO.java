package com.example.tarefaappmobileagenda.dao;

import com.example.tarefaappmobileagenda.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();
    private static int contadorDeId = 1; // acrescenta quando é necessario salvar.

    //salva informacoes dentro de uma base de dados
    public void salva(Personagem personagemSalvo) {
        personagemSalvo.setId(contadorDeId);
        personagens.add(personagemSalvo);   //add personagens
        contadorDeId++;
        atulizaId();

    }

    private void atulizaId() {
        contadorDeId++;
    }

    //possibilita editar o campo escolhido.
    public void editar(Personagem personagem) {
        Personagem personagemEscolhido = buscaPersonagemId(personagem);
        if (personagemEscolhido != null) {
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido); //coloca na posicao ideal.
            personagens.set(posicaoDoPersonagem, personagem);
        }

    }

    private Personagem buscaPersonagemId(Personagem personagem) { //idenfica o posicionamento do personagem.
        for (Personagem p :
                personagens) {
            if (p.getId() == personagem.getId()) {
                return p;         //armazena informaçoes.
            }
        }
        return null;
    }

    //busca todas as informaçoes salvas dentro da lista.
    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    } //metodo de retorno.

    public void remove(Personagem personagem){ //metodo para remover item dentro da classe de persistencia.
        Personagem personagemDevolvido = buscaPersonagemId(personagem);
        if (personagemDevolvido != null){
            personagens.remove(personagemDevolvido);
        }
    }
}
