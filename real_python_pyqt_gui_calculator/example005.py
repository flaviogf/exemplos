import sys

from PyQt5.QtWidgets import QApplication, QFormLayout, QLineEdit, QWidget

app = QApplication(sys.argv)

window = QWidget()
layout = QFormLayout()

window.setWindowTitle('QFormLayout')
window.setLayout(layout)

layout.addRow('Name', QLineEdit())
layout.addRow('Age', QLineEdit())
layout.addRow('Job', QLineEdit())
layout.addRow('Hobies', QLineEdit())

window.show()

sys.exit(app.exec_())
