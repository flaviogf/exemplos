import UIKit

protocol IAlunoRepository {

    func insere(_ aluno: Aluno) throws
    func lista() throws -> Array<Aluno>
    func deleta(_ aluno: Aluno) throws
    func busca(por id: UUID) throws -> Aluno?
    func atualiza(_ aluno: Aluno) throws
}
