package com.example.flaviogarcia.agenda

import android.widget.EditText
import android.widget.RatingBar
import com.example.flaviogarcia.agenda.modelo.Aluno
import kotlinx.android.synthetic.main.activity_formulario.*

class FormularioViewModel(activity: FormularioActivity) {

    private var _aluno: Aluno? = null
    private val nome: EditText = activity.formulario_nome
    private val endereco: EditText = activity.formulario_endereco
    private val telefone: EditText = activity.formulario_telefone
    private val site: EditText = activity.formulario_site
    private val nota: RatingBar = activity.formulario_nota

    val aluno: Aluno
        get() = Aluno(
                id = _aluno?.id,
                nome = nome.text.toString(),
                endereco = endereco.text.toString(),
                telefone = telefone.text.toString(),
                site = site.text.toString(),
                nota = nota.progress.toDouble()
        )

    fun setFomulario(aluno: Aluno) {
        nome.setText(aluno.nome)
        endereco.setText(aluno.endereco)
        telefone.setText(aluno.telefone)
        site.setText(aluno.site)
        nota.progress = aluno.nota.toInt()
        _aluno = aluno
    }
}