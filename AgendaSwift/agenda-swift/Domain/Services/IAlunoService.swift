protocol IAlunoService {

    func insere(_ aluno: Aluno) throws
    func lista() throws -> Array<Aluno>
    func deleta(_ aluno: Aluno) throws
    func atualiza(_ aluno: Aluno) throws
}
