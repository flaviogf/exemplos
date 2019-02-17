import UIKit

extension UIImage {

    func base64(qualidade: CGFloat = 80) -> String? {
        return self.jpegData(compressionQuality: qualidade)?.base64EncodedString()
    }
}
