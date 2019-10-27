import sys

from PyQt5.QtWidgets import QApplication, QPushButton, QVBoxLayout, QWidget

app = QApplication(sys.argv)

window = QWidget()
layout = QVBoxLayout()

window.setWindowTitle('QVBoxLayout')
window.setLayout(layout)

layout.addWidget(QPushButton('Top'))
layout.addWidget(QPushButton('Center'))
layout.addWidget(QPushButton('Bottom'))

window.show()

sys.exit(app.exec_())
