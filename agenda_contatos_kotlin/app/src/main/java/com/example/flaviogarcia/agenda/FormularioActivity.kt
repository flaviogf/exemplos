package com.example.flaviogarcia.agenda

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.flaviogarcia.agenda.dao.AlunoDAO
import com.example.flaviogarcia.agenda.modelo.Aluno

class FormularioActivity : AppCompatActivity() {

    private lateinit var viewModel: FormularioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)
        viewModel = FormularioViewModel(this)
        val aluno = intent.extras?.get("aluno") as Aluno?
        if (aluno != null) viewModel.setFomulario(aluno)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_main_add -> {
                val aluno = viewModel.aluno
                val dao = AlunoDAO(this)
                if (aluno.id != null)
                    dao.atualizar(aluno)
                else
                    dao.inserir(aluno)
                Toast.makeText(this, "Aluno ${aluno.nome} adicionado com sucesso", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
