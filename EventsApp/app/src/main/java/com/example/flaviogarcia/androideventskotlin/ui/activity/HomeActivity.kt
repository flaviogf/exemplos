package com.example.flaviogarcia.androideventskotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.flaviogarcia.androideventskotlin.R
import com.example.flaviogarcia.androideventskotlin.core.token.TokenManager
import com.example.flaviogarcia.androideventskotlin.ui.adapter.HomeTabAdapter
import com.example.flaviogarcia.androideventskotlin.util.extension.configurarToolbar
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity() {

    private val tokenManager by lazy {
        TokenManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        inicializar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.menu_home, menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.menu_home_sair -> {
            tokenManager.deletar()
            startActivity<AutenticacaoActivity>()
            finish()
            true
        }
        else -> true
    }


    private fun inicializar() {
        configurarToolbar(home_toolbar as Toolbar)
        configurarTabs()
        configurarFab()
    }

    private fun configurarTabs() {
        home_view_pager.adapter = HomeTabAdapter(supportFragmentManager)
        home_tab_layout.setupWithViewPager(home_view_pager)
    }

    private fun configurarFab() {
        home_fab.setOnClickListener {
            startActivity<FormularioOcorrenciaActivity>()
        }
    }
}
