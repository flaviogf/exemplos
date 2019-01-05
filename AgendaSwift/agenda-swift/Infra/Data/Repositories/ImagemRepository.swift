import UIKit

class ImagemRepository: IImagemRepository {

    private let diretorioImagens = "\(NSHomeDirectory())/Imagens"

    func salvaImagem(do aluno: Aluno) throws {
        if !aluno.imagem.isEmpty {
            try self.cria(diretorioImagens)
            let caminhoImagem = "\(diretorioImagens)/\(UUID()).jpg"
            try Data(base64Encoded: aluno.imagem)?.write(to: URL(fileURLWithPath: caminhoImagem))
            aluno.imagem = caminhoImagem
        }
    }

    private func cria(_ diretorio: String) throws {
        if !FileManager.default.fileExists(atPath: diretorio) {
            try FileManager.default.createDirectory(at: URL(fileURLWithPath: diretorio), withIntermediateDirectories: false, attributes: nil)
        }
    }
}
