import UIKit

class AlunoViewController: UIViewController {

    @IBOutlet weak var imageViewAluno: UIImageView!

    @IBOutlet weak var textFieldNome: UITextField!

    @IBOutlet weak var textFieldEmail: UITextField!

    @IBOutlet weak var textFieldEndereco: UITextField!

    private let imagePicker = ImagePicker()

    private var presenter: IAlunoPresenter = Container.instancia.alunoPresenter

    private var aluno: Aluno?

    @IBAction func fechaTeclado(_ sender: Any) {
        self.view.endEditing(true)
    }

    @IBAction func cliqueBtnSalvar(_ sender: UIButton) {
        let nome = self.textFieldNome.text ?? ""
        let email = self.textFieldEmail.text ?? ""
        let endereco = self.textFieldEndereco.text ?? ""
        let imagem = self.imageViewAluno.image?.base64() ?? ""

        if let id = self.aluno?.id {
            self.presenter.atualiza(id: id, nome: nome, email: email, endereco: endereco, imagem: imagem)
        } else {
            self.presenter.insere(nome: nome, email: email, endereco: endereco, imagem: imagem)
        }
    }

    @IBAction func cliqueBtnFoto(_ sender: UIButton) {
        self.present(self.imagePicker.menu, animated: true)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        self.configuraImageViewAluno()
        self.configuraExibicaoAluno()
        self.configuraImagePicker()
        self.configuraPresenter()
        self.configuraNavbar()
    }

    private func configuraImageViewAluno() {
        self.imageViewAluno.arredonda()
    }

    private func configuraExibicaoAluno() {
        if let aluno = self.aluno {
            self.imageViewAluno.image = UIImage(contentsOfFile: aluno.imagem)
            self.textFieldNome.text = aluno.nome
            self.textFieldEmail.text = aluno.email
            self.textFieldEndereco.text = aluno.endereco
        }
    }

    private func configuraImagePicker() {
        self.imagePicker.delegate = self
    }

    private func configuraPresenter() {
        self.presenter.delegate = self
    }

    private func configuraNavbar() {
        self.navigationItem.title = "Aluno"
    }

    func seleciona(_ aluno: Aluno) {
        self.aluno = aluno
    }
}

extension AlunoViewController: ImagePickerDelegate {

    func exibe(_ controller: UIViewController) {
        self.present(controller, animated: true)
    }

    func seleciona(_ imagem: UIImage) {
        self.imageViewAluno.image = imagem
    }
}

extension AlunoViewController: IAlunoView {

    func alerta(titulo: String, mensagem: String) {
        let alertController = UIAlertController(title: titulo, message: mensagem, preferredStyle: .alert)
        let confirmar = UIAlertAction(title: "Confirmar", style: .default) { action in
            self.navigationController?.popViewController(animated: true)
        }
        alertController.addAction(confirmar)
        self.present(alertController, animated: true)
    }
}
