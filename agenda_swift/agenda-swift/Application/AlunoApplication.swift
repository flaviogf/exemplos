class AlunoApplication: IAlunoApplication {

    private var alunoService: IAlunoService

    init(alunoService: IAlunoService) {
        self.alunoService = alunoService
    }

    func insere(_ aluno: Aluno) throws {
        try self.alunoService.insere(aluno)
    }

    func lista() throws -> Array<Aluno> {
        return try self.alunoService.lista()
    }

    func deleta(_ aluno: Aluno) throws {
        try self.alunoService.deleta(aluno)
    }

    func atualiza(_ aluno: Aluno) throws {
        try self.alunoService.atualiza(aluno)
    }
}
