package com.example.flaviogarcia.androideventskotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Patterns
import com.example.flaviogarcia.androideventskotlin.EventsApplication
import com.example.flaviogarcia.androideventskotlin.R
import com.example.flaviogarcia.androideventskotlin.callback.AutenticacaoCallback
import com.example.flaviogarcia.androideventskotlin.core.token.TokenManager
import com.example.flaviogarcia.androideventskotlin.dto.AutenticacaoRequestDto
import kotlinx.android.synthetic.main.activity_autenticacao.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.startActivity
import java.util.regex.Pattern

class AutenticacaoActivity : AppCompatActivity() {

    private val tokenManager by lazy {
        TokenManager(this)
    }

    private val service = EventsApplication.autenticacaoService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autenticacao)
        inicializar()
    }

    private fun inicializar() {
        autenticar()
        autenticacao_btn_entrar.setOnClickListener {
            entrar()
        }
    }

    private fun entrar() {
        if (validar()) {
            val dto = AutenticacaoRequestDto(
                    email = autenticacao_email.text.toString(),
                    senha = autenticacao_senha.text.toString()
            )
            service.login(dto).enqueue(AutenticacaoCallback { error, response ->
                when (error) {
                    null -> response?.apply {
                        tokenManager.salvar(token)
                        autenticar()
                    }
                    else -> snackbar(autenticacao_constraint_layout, error)
                }
            })
        }
    }

    private fun autenticar() {
        val token = tokenManager.ler()
        if (token.isNotEmpty()) {
            startActivity<HomeActivity>()
            finish()
        }
    }

    private fun validarEmail() = when (Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), autenticacao_email.text)) {
        true -> {
            autenticacao_email_layout.error = ""
            true
        }
        else -> {
            autenticacao_email_layout.error = "E-mail inválido"
            false
        }
    }

    private fun validarSenha() = when (autenticacao_senha.text.trim().isNotEmpty()) {
        true -> {
            autenticacao_senha_layout.error = ""
            true
        }
        else -> {
            autenticacao_senha_layout.error = "Senha inválida"
            false
        }
    }

    private fun validar() = validarEmail() && validarSenha()
}
