import sys

from PyQt5.QtCore import Qt
from PyQt5.QtWidgets import (QApplication, QGridLayout, QLineEdit, QMainWindow,
                             QPushButton, QVBoxLayout, QWidget)


class View(QMainWindow):
    def __init__(self, parent=None):
        super().__init__(parent)

        self.setWindowTitle('PyCalc')
        self.setFixedSize(235, 235)

        self._layout = QVBoxLayout()
        self._display = QLineEdit()
        self._grid = QGridLayout()
        self._widget = QWidget()
        self._buttons = []

        self._widget.setLayout(self._layout)

        self.setCentralWidget(self._widget)

        self._add_display()

        self._add_grid()

    @property
    def buttons(self):
        return self._buttons

    @property
    def display_text(self):
        return self._display.text()

    @display_text.setter
    def display_text(self, text):
        self._display.setText(text)

    def _add_display(self):
        self._display.setAlignment(Qt.AlignRight)
        self._display.setFixedHeight(35)
        self._display.setReadOnly(True)

        self._layout.addWidget(self._display)

    def _add_grid(self):
        items = [
            ['7', '8', '9', '/', 'C'],
            ['4', '5', '6', '*', '('],
            ['1', '2', '3', '-', ')'],
            ['0', '00', '.', '+', '=']
        ]

        self._buttons = [(QPushButton(button), {'x': x, 'y': y})
                         for x, row in enumerate(items)
                         for y, button in enumerate(row)]

        for button, position in self._buttons:
            self._grid.addWidget(button, position['x'], position['y'])

        self._layout.addLayout(self._grid)


class Controller:
    def __init__(self, view):
        self._view = view

    def connect(self):
        for button, _ in self._view.buttons:
            button.clicked.connect(lambda: self.on_click_button(button))

    def on_click_button(self, button):
        ...


def main():
    pycalc = QApplication(sys.argv)
    view = View()
    controller = Controller(view)
    view.show()
    controller.connect()
    sys.exit(pycalc.exec())


if __name__ == '__main__':
    main()
