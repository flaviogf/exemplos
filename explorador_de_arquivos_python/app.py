import re
from os import path, listdir

from flask import Flask, render_template, request, redirect, url_for

app = Flask(__name__)


def formata_nome_diretorio(diretorio):
    return re.split(r"\\{1,2}", diretorio)


@app.route("/")
def index():
    return render_template("index.html")


@app.route("/diretorio", methods=["POST"])
def busca_diretorio():
    diretorio = request.form["diretorio"]
    if path.isdir(diretorio):
        return render_template("arquivos.html",
                               arquivos=listdir(diretorio),
                               diretorio=formata_nome_diretorio(diretorio)
                               )
    return redirect(url_for("index"))


if __name__ == '__main__':
    app.run()
