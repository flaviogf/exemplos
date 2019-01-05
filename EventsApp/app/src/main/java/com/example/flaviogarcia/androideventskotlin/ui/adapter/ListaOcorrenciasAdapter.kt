package com.example.flaviogarcia.androideventskotlin.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flaviogarcia.androideventskotlin.R
import com.example.flaviogarcia.androideventskotlin.model.ListaOcorrenciasTipo
import com.example.flaviogarcia.androideventskotlin.model.Ocorrencia
import com.example.flaviogarcia.androideventskotlin.util.extension.formatoBrasileiro
import com.example.flaviogarcia.androideventskotlin.util.extension.limite
import kotlinx.android.synthetic.main.item_ocorrencias.view.*

class ListaOcorrenciasAdapter(
        private val ocorrencias: List<Ocorrencia>,
        private val tipo: ListaOcorrenciasTipo,
        private val listener: ListaOcorrenciaAdapterListener
) : RecyclerView.Adapter<ListaOcorrenciasAdapter.ListaOcorrenciasViewHolder>() {

    class ListaOcorrenciasViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface ListaOcorrenciaAdapterListener {
        fun onMenuItemOcorrenciaClick(ocorrencia: Ocorrencia, view: View)
        fun onItemOcorrenciaClick(ocorrencia: Ocorrencia)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaOcorrenciasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ocorrencias, parent, false)
        view.item_ocorrencia_menu.visibility = if (tipo == ListaOcorrenciasTipo.Todas) View.INVISIBLE else View.VISIBLE
        return ListaOcorrenciasViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListaOcorrenciasViewHolder, posicao: Int) {
        holder.itemView?.apply {
            val ocorrencia = ocorrencias[posicao]
            item_ocorrencia_menu.setOnClickListener { listener.onMenuItemOcorrenciaClick(ocorrencia, it) }
            item_ocorrencia_card_view.setOnClickListener { listener.onItemOcorrenciaClick(ocorrencia) }
            item_ocorrencia_titulo.text = ocorrencia.tipoOcorrencia.nomenclatura.limite(30)
            item_ocorrencia_data.text = ocorrencia.dataAcontecimento.formatoBrasileiro()
            item_ocorrencia_descricao.text = ocorrencia.resumo.limite(30)
        }
    }

    override fun getItemCount() = ocorrencias.size
}
