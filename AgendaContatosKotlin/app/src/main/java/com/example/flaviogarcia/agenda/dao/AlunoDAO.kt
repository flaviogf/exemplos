package com.example.flaviogarcia.agenda.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.flaviogarcia.agenda.modelo.Aluno

class AlunoDAO(context: Context?) : SQLiteOpenHelper(
        context,
        "Agenda",
        null,
        5) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE  ALUNO(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOME TEXT, ENDERECO TEXT, TELEFONE TEXT, SITE TEXT, NOTA FLOAT)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val sql = "DROP TABLE IF EXISTS ALUNO"
        db?.execSQL(sql)
        onCreate(db)
    }

    fun inserir(aluno: Aluno) {
        val content = contentValues(aluno)
        writableDatabase.insert("ALUNO", null, content)
        close()
    }


    fun buscar(): List<Aluno> {
        val sql = "SELECT * FROM ALUNO"
        val cursor = readableDatabase.rawQuery(sql, null)
        var alunos = mutableListOf<Aluno>()
        while (cursor.moveToNext()) {
            val aluno = Aluno(
                    id = cursor.getInt(cursor.getColumnIndex("ID")),
                    nome = cursor.getString(cursor.getColumnIndex("NOME")),
                    endereco = cursor.getString(cursor.getColumnIndex("ENDERECO")),
                    telefone = cursor.getString(cursor.getColumnIndex("TELEFONE")),
                    site = cursor.getString(cursor.getColumnIndex("SITE")),
                    nota = cursor.getDouble(cursor.getColumnIndex("NOTA"))
            )
            alunos.add(aluno)
        }
        return alunos
    }

    fun deletar(aluno: Aluno) {
        val db = readableDatabase
        val params = arrayOf(aluno.id.toString())
        db.delete("ALUNO", "id = ?", params)
        db.close()
    }

    fun atualizar(aluno: Aluno) {
        val db = writableDatabase
        val params = arrayOf(aluno.id.toString())
        val contentValues = contentValues(aluno)
        db.update("ALUNO", contentValues, "id = ?", params)
        db.close()
    }

    private fun contentValues(aluno: Aluno): ContentValues {
        val content = ContentValues()
        content.put("NOME", aluno.nome)
        content.put("ENDERECO", aluno.endereco)
        content.put("TELEFONE", aluno.telefone)
        content.put("SITE", aluno.site)
        content.put("NOTA", aluno.nota)
        return content
    }
}