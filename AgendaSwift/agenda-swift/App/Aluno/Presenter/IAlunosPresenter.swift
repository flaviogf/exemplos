import UIKit

protocol IAlunosPresenter {

    var delegate: IAlunosView? { get set }

    func lista()
    func deleta(_ aluno: Aluno)
}
