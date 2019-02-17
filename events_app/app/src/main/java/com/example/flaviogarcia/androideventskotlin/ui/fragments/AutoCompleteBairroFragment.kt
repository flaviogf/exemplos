package com.example.flaviogarcia.androideventskotlin.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.flaviogarcia.androideventskotlin.EventsApplication
import com.example.flaviogarcia.androideventskotlin.R
import com.example.flaviogarcia.androideventskotlin.callback.ListaBairroCallback
import com.example.flaviogarcia.androideventskotlin.core.token.TokenManager
import com.example.flaviogarcia.androideventskotlin.model.Bairro
import kotlinx.android.synthetic.main.activity_formulario_ocorrencia.view.*
import kotlinx.android.synthetic.main.fragment_auto_complete_bairro.*
import org.jetbrains.anko.design.snackbar

class AutoCompleteBairroFragment : Fragment() {

    private lateinit var onClickItemListener: (Bairro) -> Unit
    private var bairro: Bairro? = null

    companion object {

        fun instance(bairro: Bairro?, onClickItemListener: (Bairro) -> Unit): Fragment {
            val fragment = AutoCompleteBairroFragment()
            fragment.onClickItemListener = onClickItemListener
            fragment.bairro = bairro
            return fragment
        }
    }

    private val tokenManager by lazy {
        TokenManager(context!!)
    }
    private val token by lazy {
        tokenManager.ler()
    }
    private val viewActivity by lazy {
        activity?.window?.decorView
    }

    private val service = EventsApplication.bairroService
    private var bairros = emptyList<Bairro>()

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_auto_complete_bairro, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurar()
    }

    override fun onResume() {
        super.onResume()
        buscar()
    }

    private fun atualizar(bairros: List<Bairro>) {
        this.bairros = bairros
        configurar()
    }

    private fun buscar() = service.listar(token).enqueue(ListaBairroCallback { error, response ->
        when (error) {
            null -> response?.also {
                atualizar(it)
            }
            else -> viewActivity?.apply {
                snackbar(formulario_ocorrencia_coordinator, error)
            }
        }
    })

    private fun configurar() = with(fragment_auto_complete_bairro_text_view) {
        val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, bairros)
        setAdapter(adapter)
        bairro?.also {
            setText(it.nome)
            onClickItemListener(it)
        }
        setOnItemClickListener { parent, _, posicao, _ ->
            val bairro = parent.getItemAtPosition(posicao) as Bairro
            onClickItemListener(bairro)
        }
    }
}
