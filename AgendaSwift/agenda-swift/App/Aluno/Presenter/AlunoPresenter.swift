import UIKit

class AlunoPresenter: IAlunoPresenter {

    private var alunoApplication: IAlunoApplication

    var delegate: IAlunoView?

    init(alunoApplication: IAlunoApplication) {
        self.alunoApplication = alunoApplication
    }

    func insere(nome: String, email: String, endereco: String, imagem: String) {
        do {
            let aluno = Aluno(nome: nome, email: email, endereco: endereco, imagem: imagem)
            try self.alunoApplication.insere(aluno)
            self.delegate?.alerta(titulo: "Aluno", mensagem: "Inserido com sucesso")
        } catch {
            self.delegate?.alerta(titulo: "Aluno", mensagem: "Não foi possivel inserir o aluno \(nome)")
        }
    }

    func atualiza(id: UUID, nome: String, email: String, endereco: String, imagem: String) {
        do {
            let aluno = Aluno(nome: nome, email: email, endereco: endereco, imagem: imagem, id: id)
            try self.alunoApplication.atualiza(aluno)
            self.delegate?.alerta(titulo: "Aluno", mensagem: "Atualizado com sucesso")
        } catch {
            self.delegate?.alerta(titulo: "Aluno", mensagem: "Não foi possível atualizar o aluno \(nome)")
        }
    }
}
