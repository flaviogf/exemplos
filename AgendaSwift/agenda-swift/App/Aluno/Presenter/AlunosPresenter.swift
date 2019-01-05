class AlunosPresenter: IAlunosPresenter {

    private var alunoApplication: IAlunoApplication

    var delegate: IAlunosView? = nil

    init(alunoApplication: IAlunoApplication) {
        self.alunoApplication = alunoApplication
    }

    func lista() {
        do {
            let alunos = try self.alunoApplication.lista()
            self.delegate?.exibe(alunos)
        } catch {
            self.delegate?.alerta(titulo: "Alunos", mensagem: "Não foi possível encontrar nenhum aluno")
        }
    }

    func deleta(_ aluno: Aluno) {
        do {
            try self.alunoApplication.deleta(aluno)
        } catch {
            self.delegate?.alerta(titulo: "Aluno", mensagem: "Não foi possível excluir o aluno")
        }
    }
}
