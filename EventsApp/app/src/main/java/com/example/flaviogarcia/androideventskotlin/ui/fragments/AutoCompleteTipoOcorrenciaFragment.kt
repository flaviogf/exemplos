package com.example.flaviogarcia.androideventskotlin.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.flaviogarcia.androideventskotlin.EventsApplication
import com.example.flaviogarcia.androideventskotlin.R
import com.example.flaviogarcia.androideventskotlin.callback.ListaTipoOcorrenciaCallback
import com.example.flaviogarcia.androideventskotlin.core.token.TokenManager
import com.example.flaviogarcia.androideventskotlin.model.TipoOcorrencia
import kotlinx.android.synthetic.main.activity_formulario_ocorrencia.view.*
import kotlinx.android.synthetic.main.fragment_auto_complete_tipo_ocorrencia.*
import org.jetbrains.anko.design.snackbar

class AutoCompleteTipoOcorrenciaFragment : Fragment() {

    private lateinit var onClickItemListener: (TipoOcorrencia) -> Unit
    private var tipoOcorrencia: TipoOcorrencia? = null

    companion object {

        fun instance(tipoOcorrencia: TipoOcorrencia?, onClickItemListener: (TipoOcorrencia) -> Unit): Fragment {
            val fragment = AutoCompleteTipoOcorrenciaFragment()
            fragment.onClickItemListener = onClickItemListener
            fragment.tipoOcorrencia = tipoOcorrencia
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

    private val service = EventsApplication.tipoOcorrenciaService
    private var tipos = emptyList<TipoOcorrencia>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_auto_complete_tipo_ocorrencia, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurar()
    }

    override fun onResume() {
        super.onResume()
        buscar()
    }

    private fun atualizar(tipos: List<TipoOcorrencia>) {
        this.tipos = tipos
        configurar()
    }

    private fun buscar() = service.listar(token).enqueue(ListaTipoOcorrenciaCallback { error, response ->
        when (error) {
            null -> response?.also {
                atualizar(it)
            }
            else -> viewActivity?.apply {
                snackbar(formulario_ocorrencia_coordinator, error)
            }
        }
    })

    private fun configurar() = with(fragment_auto_complete_tipo_ocorrencia_text_view) {
        val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, tipos)
        setAdapter(adapter)
        tipoOcorrencia?.also {
            setText(it.nomenclatura)
            onClickItemListener(it)
        }
        setOnItemClickListener { parent, _, posicao, _ ->
            val tipoOcorrencia = parent?.getItemAtPosition(posicao) as TipoOcorrencia
            onClickItemListener(tipoOcorrencia)
        }

    }
}
