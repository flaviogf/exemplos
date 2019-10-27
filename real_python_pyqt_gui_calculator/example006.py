import sys

from PyQt5.QtWidgets import (QApplication, QDialog, QDialogButtonBox,
                             QFormLayout, QLineEdit, QVBoxLayout)


class CustomDialog(QDialog):
    def __init__(self, parent=None):
        super().__init__(parent=parent)

        layout = QVBoxLayout()
        form = QFormLayout()
        buttons = QDialogButtonBox()

        layout.addLayout(form)
        layout.addWidget(buttons)

        form.addRow('Name', QLineEdit())
        form.addRow('Age', QLineEdit())
        form.addRow('Job', QLineEdit())
        form.addRow('Hobies', QLineEdit())

        buttons.setStandardButtons(
            QDialogButtonBox.Cancel | QDialogButtonBox.Ok)

        self.setWindowTitle('QDialog')
        self.setLayout(layout)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    dialog = CustomDialog()
    dialog.show()
    sys.exit(app.exec_())
