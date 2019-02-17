import UIKit

protocol IAlunoPresenter {

    var delegate: IAlunoView? { get set }

    func insere(nome: String, email: String, endereco: String, imagem: String)

    func atualiza(id: UUID, nome: String, email: String, endereco: String, imagem: String)
}
