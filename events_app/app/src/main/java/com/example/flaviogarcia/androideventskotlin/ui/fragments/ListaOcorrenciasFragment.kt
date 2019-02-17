package com.example.flaviogarcia.androideventskotlin.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flaviogarcia.androideventskotlin.EventsApplication
import com.example.flaviogarcia.androideventskotlin.R
import com.example.flaviogarcia.androideventskotlin.callback.DeletarOcorrenciaCallback
import com.example.flaviogarcia.androideventskotlin.callback.ListaOcorrenciaCallback
import com.example.flaviogarcia.androideventskotlin.core.token.TokenManager
import com.example.flaviogarcia.androideventskotlin.model.ListaOcorrenciasTipo
import com.example.flaviogarcia.androideventskotlin.model.Ocorrencia
import com.example.flaviogarcia.androideventskotlin.ui.activity.FormularioOcorrenciaActivity
import com.example.flaviogarcia.androideventskotlin.ui.adapter.ListaOcorrenciasAdapter
import com.example.flaviogarcia.androideventskotlin.ui.popup.PopupItensOcorrencia
import kotlinx.android.synthetic.main.activity_home.view.*
import kotlinx.android.synthetic.main.fragment_lista_ocorrencias.*
import kotlinx.android.synthetic.main.fragment_lista_ocorrencias.view.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor

class ListaOcorrenciasFragment : Fragment(), ListaOcorrenciasAdapter.ListaOcorrenciaAdapterListener {

    private val tokenManager by lazy {
        TokenManager(context!!)
    }
    private val token by lazy {
        tokenManager.ler()
    }
    private val viewActivity by lazy {
        activity?.window?.decorView
    }

    private val service = EventsApplication.ocorrenciaService
    private var ocorrencias = emptyList<Ocorrencia>()
    private var tipo = ListaOcorrenciasTipo.Todas

    companion object {

        fun instance(tipo: ListaOcorrenciasTipo): Fragment {
            val fragment = ListaOcorrenciasFragment()
            val bundle = Bundle()
            bundle.putSerializable("tipo", tipo)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tipo = arguments?.getSerializable("tipo") as ListaOcorrenciasTipo
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lista_ocorrencias, container, false)
        view.fragment_lista_ocorrencias_swiperefresh?.setOnRefreshListener {
            buscar()
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        buscar()
    }

    private fun configurarListaOcorrencias(response: List<Ocorrencia>) = with(fragment_lista_ocorrencias_recycler_view) {
        ocorrencias = response
        adapter = ListaOcorrenciasAdapter(ocorrencias, tipo, this@ListaOcorrenciasFragment)
        layoutManager = LinearLayoutManager(context)
        setHasFixedSize(true)
    }

    override fun onMenuItemOcorrenciaClick(ocorrencia: Ocorrencia, view: View) = PopupItensOcorrencia(context!!, view).show {
        when (it.itemId) {
            R.id.menu_lista_ocorrencias_deletar -> {
                exibirAlertaConfirmar(ocorrencia)
                true
            }
            R.id.menu_lista_ocorrencias_editar -> {
                activity?.run {
                    startActivity(intentFor<FormularioOcorrenciaActivity>("ocorrencia" to ocorrencia))
                }
                true
            }
            else -> true
        }
    }

    override fun onItemOcorrenciaClick(ocorrencia: Ocorrencia) {
        viewActivity?.run {
            //startActivity(intentFor<FormularioOcorrenciaActivity>("ocorrencia" to ocorrencia))
            snackbar(home_coordinator_layout, ocorrencia.usuario.nome)
        }
    }

    private fun exibirAlertaConfirmar(ocorrencia: Ocorrencia) = AlertDialog.Builder(context)
            .setTitle("Ocorrência")
            .setMessage("Deseja realmente deletar a ocorrencia?")
            .setNegativeButton("Cancelar", null)
            .setPositiveButton("Confirmar") { _, _ -> deletar(ocorrencia) }
            .show()

    private fun buscar() = when (tipo) {
        ListaOcorrenciasTipo.Usuario -> usuario()
        ListaOcorrenciasTipo.Todas -> todas()
    }

    private fun todas() = service.listar(token).enqueue(ListaOcorrenciaCallback { error, response ->
        when (error) {
            null -> response?.also {
                configurarListaOcorrencias(it)
            }
            else -> viewActivity?.apply {
                snackbar(home_coordinator_layout, error)
            }
        }
        fragment_lista_ocorrencias_swiperefresh.isRefreshing = false
    })

    private fun usuario() = service.listarOcorrenciasUsuario(token).enqueue(ListaOcorrenciaCallback { error, response ->
        when (error) {
            null -> response?.also {
                configurarListaOcorrencias(it)
            }
            else -> viewActivity?.apply {
                snackbar(home_coordinator_layout, error)
            }
        }
        fragment_lista_ocorrencias_swiperefresh.isRefreshing = false
    })

    private fun deletar(ocorrencia: Ocorrencia) {
        service.deletar(token, ocorrencia._id).enqueue(DeletarOcorrenciaCallback { error ->
            when (error) {
                null -> viewActivity?.apply {
                    snackbar(home_coordinator_layout, "Ocorrência deletada com sucesso")
                    buscar()
                }
                else -> viewActivity?.apply {
                    snackbar(home_coordinator_layout, error)
                }
            }
        })
    }
}
