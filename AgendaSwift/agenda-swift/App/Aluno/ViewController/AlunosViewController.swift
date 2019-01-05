import UIKit

class AlunosViewController: UIViewController {

    @IBOutlet weak var tableViewAlunos: UITableView!

    private var presenter = Container.instancia.alunosPresenter

    private var alunos = Array<Aluno>() {
        didSet {
            self.alunosFiltrados = self.alunos
        }
    }

    private var alunosFiltrados = Array<Aluno>() {
        didSet {
            self.tableViewAlunos.reloadData()
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        self.configuraSearchController()
        self.configuraPresenter()
        self.configuraTableView()
        self.configuraNavbar()
    }

    override func viewWillAppear(_ animated: Bool) {
        self.listaAlunos()
    }

    private func configuraSearchController() {
        let searchController = UISearchController(searchResultsController: nil)
        searchController.obscuresBackgroundDuringPresentation = false
        searchController.searchBar.placeholder = "Pesquisar"
        searchController.searchBar.delegate = self
        self.navigationItem.searchController = searchController
    }

    private func configuraPresenter() {
        self.presenter.delegate = self
    }

    private func configuraTableView() {
        self.tableViewAlunos.register(AlunoViewCell.nib, forCellReuseIdentifier: AlunoViewCell.id)
        self.tableViewAlunos.dataSource = self
        self.tableViewAlunos.delegate = self
    }

    private func configuraNavbar() {
        self.navigationItem.title = "Alunos"
    }

    private func listaAlunos() {
        self.presenter.lista()
    }
}

extension AlunosViewController: UITableViewDataSource {

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.alunosFiltrados.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let celula = tableView.dequeueReusableCell(withIdentifier: AlunoViewCell.id, for: indexPath) as! AlunoViewCell
        celula.aluno = self.alunosFiltrados[indexPath.row]
        return celula
    }
}

extension AlunosViewController: UITableViewDelegate {

    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 85
    }

    func tableView(_ tableView: UITableView, editActionsForRowAt indexPath: IndexPath) -> [UITableViewRowAction]? {
        let excluir = UITableViewRowAction(style: .destructive, title: "Excluir") { action, path in
            let aluno = self.alunosFiltrados[path.row]
            self.presenter.deleta(aluno)
            self.listaAlunos()
        }

        return [excluir]
    }

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let aluno = self.alunosFiltrados[indexPath.row]
        let alunoViewController: AlunoViewController = AppDelegate.Storyboard.Aluno.viewController(id: "AlunoViewController")
        alunoViewController.seleciona(aluno)
        self.navigationController?.pushViewController(alunoViewController, animated: true)
    }
}

extension AlunosViewController: UISearchBarDelegate {

    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        self.alunosFiltrados = self.alunos.filter {
            return searchText.isEmpty || $0.nome.contains(searchText)
        }
    }
}

extension AlunosViewController: IAlunosView {

    func exibe(_ alunos: Array<Aluno>) {
        self.alunos = alunos
    }

    func alerta(titulo: String, mensagem: String) {
        let alertController = UIAlertController(title: titulo, message: mensagem, preferredStyle: .alert)
        let confirmar = UIAlertAction(title: "Confirmar", style: .default)
        alertController.addAction(confirmar)
        self.present(alertController, animated: true)
    }
}
