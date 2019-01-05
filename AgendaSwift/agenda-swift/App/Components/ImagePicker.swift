import UIKit

protocol ImagePickerDelegate {
    func exibe(_ controller: UIViewController)
    func seleciona(_ imagem: UIImage)
}

class ImagePicker: NSObject, UIImagePickerControllerDelegate, UINavigationControllerDelegate {

    var delegate: ImagePickerDelegate?

    var menu: UIAlertController {
        let imagePickerController = UIImagePickerController()
        imagePickerController.delegate = self

        let biblioteca = UIAlertAction(title: "Biblioteca", style: .default) { action in
            imagePickerController.sourceType = .photoLibrary
            self.delegate?.exibe(imagePickerController)
        }
        let camera = UIAlertAction(title: "Camera", style: .default) { action in
            if UIImagePickerController.isSourceTypeAvailable(.camera) {
                imagePickerController.sourceType = .photoLibrary
                self.delegate?.exibe(imagePickerController)
            }
        }
        let cancelar = UIAlertAction(title: "Cancelar", style: .cancel)

        let alertController = UIAlertController(title: "Atenção", message: "Escolha uma das opções", preferredStyle: .actionSheet)
        alertController.addAction(biblioteca)
        alertController.addAction(camera)
        alertController.addAction(cancelar)

        return alertController
    }

    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey: Any]) {
        if let imagem = info[UIImagePickerController.InfoKey.originalImage] as? UIImage {
            self.delegate?.seleciona(imagem)
            picker.dismiss(animated: true)
        }
    }
}
