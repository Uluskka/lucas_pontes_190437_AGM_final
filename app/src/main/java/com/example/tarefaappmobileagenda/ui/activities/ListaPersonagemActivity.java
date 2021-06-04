package com.example.tarefaappmobileagenda.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tarefaappmobileagenda.R;
import com.example.tarefaappmobileagenda.dao.PersonagemDAO;
import com.example.tarefaappmobileagenda.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.tarefaappmobileagenda.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Personagens";
    private final PersonagemDAO dao = new PersonagemDAO(); // declarando o index dao para utiliza-lo.
    private ArrayAdapter<Personagem> adapter;

    //override para lista de personagens.
    @Override
    protected void onCreate(Bundle savedInstanceState) { //configura a lista.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle(TITULO_APPBAR); //seta o titulo.
        configuraFabNovoPersonagem();
        configuraLista();


    }

    //metodo para configura e add novo personagem.
    private void configuraFabNovoPersonagem() {
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abreFormulario();       //direciona para o formulario.
            }
        });
    }

    private void abreFormulario() {
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }

    @Override
    protected void onResume() {  //salva os dados digitados no aplicativo para nao serem apagados.
        super.onResume();
        atulizaPersonagem();

    }

    private void atulizaPersonagem() {
        adapter.clear();
        adapter.addAll(dao.todos()); //atuliza minha classe persistencia.
    }

    private void remove(Personagem personagem) { //remove os dados que voce deseja apagar sem duplicar.
        dao.remove(personagem);
        adapter.remove(personagem);
    }

    @Override
    //metodo para remover personagem da litas.
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_personagem_menu, menu);
        //menu.add("Remover");
    }

    @Override
    //remove informacoes desejadas.
    public boolean onContextItemSelected(@NonNull MenuItem item) {     //return para o adapater.
        return configuraMenu(item);


    }

    private boolean configuraMenu(@NonNull MenuItem item) {
        //metodo feito para remover item desejado da lista dando a escolha se voce quer mesmo remover ou nao.
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_personagem_menu_remover) {
            new AlertDialog.Builder(this)
                    .setTitle("Removendo Personagem")
                    .setMessage("Tem Certeza que deseja remover?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                            Personagem personagemEscolhido = adapter.getItem(menuInfo.position);
                            remove(personagemEscolhido);
                        }
                    })
                    .setNegativeButton("Não", null)
                    .show();

        }
        return super.onContextItemSelected(item);
    }

    private void configuraLista() {
        ListView listaDePersonagens = findViewById(R.id.activity_main_list_personagem); //Lista de personagens.
        listaDePersonagens(listaDePersonagens);
        configuraItemPorClique(listaDePersonagens);
        registerForContextMenu(listaDePersonagens);
    }

    private void configuraItemPorClique(ListView listaDePersonagens) {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) { //Faz uma meio de entrada com os dados digitados
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao);
                abreFormularioEditar(personagemEscolhido);


            }

            //formulario feito para editar as informacoes.
            private void abreFormularioEditar(Personagem personagemEscolhido) {
                Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
                vaiParaFormulario.putExtra(CHAVE_PERSONAGEM, personagemEscolhido);
                startActivity(vaiParaFormulario);
            }
        });
    }

    private void listaDePersonagens(ListView listaDePersonagens) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaDePersonagens.setAdapter(adapter);
    }

}
