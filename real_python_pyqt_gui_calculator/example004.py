import sys

from PyQt5.QtWidgets import QApplication, QGridLayout, QPushButton, QWidget

app = QApplication(sys.argv)

window = QWidget()
layout = QGridLayout()

window.setWindowTitle('QBoxLayout')
window.setLayout(layout)

layout.addWidget(QPushButton('Button (0, 0)'), 0, 0)
layout.addWidget(QPushButton('Button (0, 1)'), 0, 1)
layout.addWidget(QPushButton('Button (0, 2)'), 0, 2)


layout.addWidget(QPushButton('Button (1, 0)'), 1, 0)
layout.addWidget(QPushButton('Button (1, 1)'), 1, 1)
layout.addWidget(QPushButton('Button (1, 2)'), 1, 2)

layout.addWidget(QPushButton('Button (2, 0)'), 2, 0)
layout.addWidget(QPushButton('Button (2, 1) + 2 columns span'), 2, 1, 1, 2)

window.show()

sys.exit(app.exec_())
