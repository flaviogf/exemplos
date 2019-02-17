package com.example.flaviogarcia.androideventskotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.example.flaviogarcia.androideventskotlin.EventsApplication
import com.example.flaviogarcia.androideventskotlin.R
import com.example.flaviogarcia.androideventskotlin.callback.AtualizarOcorrenciaCallback
import com.example.flaviogarcia.androideventskotlin.callback.CadastroOcorrenciaCallback
import com.example.flaviogarcia.androideventskotlin.core.token.TokenManager
import com.example.flaviogarcia.androideventskotlin.dto.CadastroOcorrenciaDto
import com.example.flaviogarcia.androideventskotlin.model.Ocorrencia
import com.example.flaviogarcia.androideventskotlin.ui.fragments.AutoCompleteBairroFragment
import com.example.flaviogarcia.androideventskotlin.ui.fragments.AutoCompleteTipoOcorrenciaFragment
import com.example.flaviogarcia.androideventskotlin.ui.fragments.DatePickerFragment
import com.example.flaviogarcia.androideventskotlin.ui.fragments.SpinnerPeriodoFragment
import com.example.flaviogarcia.androideventskotlin.util.extension.configurarFragments
import com.example.flaviogarcia.androideventskotlin.util.extension.configurarToolbar
import com.example.flaviogarcia.androideventskotlin.util.extension.formatoApi
import kotlinx.android.synthetic.main.activity_formulario_ocorrencia.*
import kotlinx.android.synthetic.main.fragment_spinner_periodo.*
import org.jetbrains.anko.design.snackbar

class FormularioOcorrenciaActivity : AppCompatActivity() {

    private val tokenManager by lazy {
        TokenManager(this)
    }
    private val token by lazy {
        tokenManager.ler()
    }
    private val ocorrencia by lazy {
        intent?.extras?.getParcelable("ocorrencia") as Ocorrencia?
    }

    private val ocorrenciaService = EventsApplication.ocorrenciaService
    private val dto = CadastroOcorrenciaDto()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_ocorrencia)
        inicializar()
    }

    private fun inicializar() {
        formulario_ocorrencia_fab.setOnClickListener {
            if (ocorrencia != null) {
                atualizar()
            } else {
                cadastrar()
            }
        }
        configurarFragments(
                R.id.formulario_ocorrencia_bairro to AutoCompleteTipoOcorrenciaFragment.instance(ocorrencia?.tipoOcorrencia) { tipoOcorrencia ->
                    dto.tipoOcorrencia = tipoOcorrencia._id
                },
                R.id.formulario_ocorrencia_tipos to AutoCompleteBairroFragment.instance(ocorrencia?.bairro) { bairro ->
                    dto.bairro = bairro._id
                },
                R.id.formulario_ocorrencia_data to DatePickerFragment.instance(ocorrencia?.dataAcontecimento) { data ->
                    dto.dataAcontecimento = data.formatoApi()
                },
                R.id.formulario_ocorrencia_periodo to SpinnerPeriodoFragment.instance(ocorrencia?.periodo)
        )
        configurarToolbar(
                toolbar = formulario_ocorrencia_toolbar as Toolbar,
                titulo = "Nova Ocorrência",
                upNavigation = true
        )
        iniciarCampos()
    }

    private fun iniciarCampos() {
        ocorrencia?.apply {
            formulario_ocorrencia_numero.setText(numero.toString())
            formulario_ocorrencia_resumo.setText(resumo)
        }
    }

    private fun cadastrar() {
        try {
            dto.periodo = fragment_spinner_periodo_spinner.selectedItem.toString()
            dto.numero = formulario_ocorrencia_numero.text.toString().toInt()
            dto.resumo = formulario_ocorrencia_resumo.text.toString()
            ocorrenciaService.cadastrar(token, dto).enqueue(CadastroOcorrenciaCallback(this::cadastrar))
        } catch (e: Exception) {
            snackbar(formulario_ocorrencia_coordinator, "Verifique os dados informados")
        }
    }

    private fun cadastrar(error: String?, code: Int?) {
        when (code) {
            201 -> code.also {
                snackbar(formulario_ocorrencia_coordinator, "Ocorrência cadastrada com sucesso")
                finish()
            }
            else -> error?.also {
                snackbar(formulario_ocorrencia_coordinator, it)
            }
        }
    }

    private fun atualizar() {
        try {
            dto.periodo = fragment_spinner_periodo_spinner.selectedItem.toString()
            dto.numero = formulario_ocorrencia_numero.text.toString().toInt()
            dto.resumo = formulario_ocorrencia_resumo.text.toString()
            ocorrenciaService.atualizar(token, dto, ocorrencia!!._id).enqueue(AtualizarOcorrenciaCallback(this::atualizar))
        } catch (e: Exception) {
            snackbar(formulario_ocorrencia_coordinator, "Verifique os dados informados")
        }
    }

    private fun atualizar(error: String?, code: Int?) {
        when (code) {
            204 -> code.also {
                snackbar(formulario_ocorrencia_coordinator, "Ocorrência atualizada com sucesso")
                finish()
            }
            else -> error?.also {
                snackbar(formulario_ocorrencia_coordinator, it)
            }
        }
    }
}
