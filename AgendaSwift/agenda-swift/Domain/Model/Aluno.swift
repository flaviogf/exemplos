import UIKit

class Aluno: CustomStringConvertible {
    let nome: String
    let email: String
    let endereco: String
    var imagem: String
    let id: UUID?

    init(nome: String = "", email: String = "", endereco: String = "", imagem: String = "", id: UUID? = nil) {
        self.nome = nome
        self.email = email
        self.endereco = endereco
        self.imagem = imagem
        self.id = id
    }

    var description: String {
        return "Aluno(nome=\(self.nome), email=\(self.email), endereco=\(self.endereco), imagem=\(self.imagem))"
    }
}
