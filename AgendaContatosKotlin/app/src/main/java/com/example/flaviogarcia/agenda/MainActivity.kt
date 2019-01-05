package com.example.flaviogarcia.agenda

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ContextMenu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.flaviogarcia.agenda.dao.AlunoDAO
import com.example.flaviogarcia.agenda.modelo.Aluno
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerForContextMenu(lista_alunos)
        main_botao_add.setOnClickListener {
            val intent = Intent(this, FormularioActivity::class.java)
            startActivity(intent)
        }
        lista_alunos.setOnItemClickListener() { _, _, posicao, _ ->
            val aluno = lista_alunos.getItemAtPosition(posicao) as Aluno
            val intent = Intent(this, FormularioActivity::class.java)
            intent.putExtra("aluno", aluno)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        buscarAlunos()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val deletar = menu?.add("Deletar")
        deletar?.setOnMenuItemClickListener {
            val info = menuInfo as AdapterView.AdapterContextMenuInfo
            val aluno = lista_alunos.getItemAtPosition(info.position) as Aluno
            AlunoDAO(this).deletar(aluno)
            buscarAlunos()
            Toast.makeText(this, "Aluno ${aluno.nome} deletado", Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun buscarAlunos() {
        val listaAlunos: List<Aluno> = AlunoDAO(this).buscar()
        val adapter = ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, listaAlunos)
        lista_alunos.adapter = adapter
    }
}
