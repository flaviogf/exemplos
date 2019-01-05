import UIKit

class AlunoViewCell: UITableViewCell {

    @IBOutlet weak var imageViewAluno: UIImageView!

    @IBOutlet weak var labelAluno: UILabel!

    var aluno: Aluno? {
        didSet {
            if let it = self.aluno {
                self.imageViewAluno.image = UIImage(contentsOfFile: it.imagem)
                self.imageViewAluno.arredonda()
                self.labelAluno.text = it.nome
            }
        }
    }

    static let nib = UINib(nibName: "AlunoViewCell", bundle: nil)

    static let id = "AlunoViewCell"
}
