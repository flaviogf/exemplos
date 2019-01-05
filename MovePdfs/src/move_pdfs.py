from os import path, listdir, chdir, rename, getcwd

from shutil import move

import shelve

import re


class MovedorDePdf:

    def __init__(self, configuracao):
        self._configuracao = configuracao

    @property
    def pdfs(self):
        return list(filter(lambda x: x.endswith('.pdf'), listdir()))

    @property
    def qtd_pdfs(self):
        return len(self.pdfs)

    def renomeia_pdfs(self):
        for livro in self.pdfs:
            nome = re.sub(r'([\d-])+', '', livro)
            rename(livro, nome.lower())

    def move_pdfs(self):
        total = self.qtd_pdfs
        for livro in self.pdfs:
            move(livro, self._configuracao.dist)
        print(f'pdf(s) movido {total}')

    def vai_para_src(self):
        chdir(self._configuracao.src)

    def vai_para_dist(self):
        chdir(self._configuracao.dist)


class Configuracao:

    chave_diretorio_usuario = 'diretorio_usuario'
    chave_config = 'config'
    chave_dist = 'dist'
    chave_src = 'src'

    def __init__(self):
        self._diretorio_usuario = ''
        self._dist = ''
        self._src = ''
        self._inicia()
        print(self, end='\n\n')

    def _inicia(self):
        with shelve.open(Configuracao.chave_config) as config:
            if Configuracao.chave_diretorio_usuario not in config \
                    and Configuracao.chave_dist not in config \
                    and Configuracao.chave_src not in config:
                return
            self._diretorio_usuario = config[Configuracao.chave_diretorio_usuario]
            self._dist = path.join(
                self._diretorio_usuario, config[Configuracao.chave_dist]
            )
            self._src = path.join(
                self._diretorio_usuario, config[Configuracao.chave_src]
            )

    def salva(self, diretorio_usuario, dist, src):
        with shelve.open(Configuracao.chave_config) as config:
            config[Configuracao.chave_diretorio_usuario] = diretorio_usuario
            config[Configuracao.chave_dist] = dist
            config[Configuracao.chave_src] = src
            self._inicia()

    @property
    def tem_configuracao(self):
        return path.isdir(self._diretorio_usuario)\
            and path.isdir(self._src)\
            and path.isdir(self._dist)

    @property
    def dist(self):
        return self._dist

    @property
    def src(self):
        return self._src

    def __str__(self):
        return f'configuracoes atuais:\n\tusuario: {self._diretorio_usuario}\n\tsrc: {self._src}\n\tdist: {self._dist}'


def main():
    configuracao = Configuracao()
    if configuracao.tem_configuracao:
        movedorDePdf = MovedorDePdf(configuracao)
        movedorDePdf.vai_para_src()
        movedorDePdf.move_pdfs()
    else:
        diretorio_usuario = input('diretorio usuario: ')
        src = input('src: ')
        dist = input('dist: ')
        configuracao.salva(diretorio_usuario, dist, src)


if __name__ == '__main__':
    main()
