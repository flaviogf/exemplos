import UIKit

extension UIImageView {

    func arredonda(tamanhoBorda: CGFloat = 1, corBorda: UIColor = UIColor.lightGray) {
        self.layer.cornerRadius = frame.width / 2
        self.layer.borderColor = corBorda.cgColor
        self.layer.borderWidth = tamanhoBorda
        self.layer.masksToBounds = true
    }
}
