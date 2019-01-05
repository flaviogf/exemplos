import os
from zipfile import ZipFile

from flask import Flask, render_template, request, Response, send_file
from werkzeug.exceptions import BadRequestKeyError

app = Flask(__name__)

PASTA_DE_DOWNLOAD = os.path.join(os.getcwd(), "downloads")
PASTA_DE_UPLOAD = os.path.join(os.getcwd(), "uploads")

ARQUIVO_ZIP = os.path.join(PASTA_DE_DOWNLOAD, 'arquivo.zip')


@app.route("/")
def index():
    return render_template("index.html")


@app.route("/arquivos")
def lista_arquivos():
    return render_template(
        "lista_arquivos.html",
        arquivos=os.listdir(PASTA_DE_UPLOAD)
    )


@app.route("/upload", methods=["POST"])
def upload():
    try:
        arquivo = request.files["arquivo"]
        diretorio_arquivo = os.path.join(PASTA_DE_UPLOAD, arquivo.filename)
        arquivo.save(diretorio_arquivo)
        return Response(status=201, content_type="application/json")
    except BadRequestKeyError:
        return Response(status=400, content_type="application/json")


@app.route("/download")
def download():
    os.chdir(PASTA_DE_UPLOAD)
    with ZipFile(ARQUIVO_ZIP, 'w') as arquivo_zip:
        for nome_arquivo in os.listdir(os.getcwd()):
            arquivo_zip.write(nome_arquivo)
    return send_file(ARQUIVO_ZIP, as_attachment=True)


if __name__ == "__main__":
    if not os.path.isdir(PASTA_DE_UPLOAD):
        os.makedirs(PASTA_DE_UPLOAD)

    if not os.path.isdir(PASTA_DE_DOWNLOAD):
        os.makedirs(PASTA_DE_DOWNLOAD)

    app.run(debug=True)
