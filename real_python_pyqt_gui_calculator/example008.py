import sys

from PyQt5.QtWidgets import (QApplication, QDialog, QHBoxLayout, QLabel,
                             QPushButton)


class CustomMainWindow(QDialog):
    def __init__(self, parent=None):
        super().__init__(parent)

        layout = QHBoxLayout()
        button = QPushButton('Click here!')
        label = QLabel('Nothing...')

        layout.addWidget(button)
        layout.addWidget(label)

        button.clicked.connect(lambda: label.setText('Hello !!!'))

        self.setWindowTitle('Signal')
        self.setLayout(layout)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = CustomMainWindow()
    window.show()
    sys.exit(app.exec())
