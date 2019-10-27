import sys

from PyQt5.QtWidgets import QApplication, QHBoxLayout, QPushButton, QWidget

app = QApplication(sys.argv)

window = QWidget()
layout = QHBoxLayout()

window.setLayout(layout)
window.setWindowTitle('QHBoxLayout')

layout.addWidget(QPushButton('Left'))
layout.addWidget(QPushButton('Center'))
layout.addWidget(QPushButton('Right'))

window.show()

sys.exit(app.exec_())
