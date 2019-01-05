import UIKit

class AlunoService: IAlunoService {

    private var alunoRepository: IAlunoRepository

    private var imagemRepository: IImagemRepository

    init(alunoRepository: IAlunoRepository, imagemRepository: IImagemRepository) {
        self.alunoRepository = alunoRepository
        self.imagemRepository = imagemRepository
    }

    func insere(_ aluno: Aluno) throws {
        try self.imagemRepository.salvaImagem(do: aluno)
        try self.alunoRepository.insere(aluno)
    }

    func lista() throws -> Array<Aluno> {
        return try self.alunoRepository.lista()
    }

    func deleta(_ aluno: Aluno) throws {
        try self.alunoRepository.deleta(aluno)
    }

    func atualiza(_ aluno: Aluno) throws {
        try self.imagemRepository.salvaImagem(do: aluno)
        try self.alunoRepository.atualiza(aluno)
    }
}
