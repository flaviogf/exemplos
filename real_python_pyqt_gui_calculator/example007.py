import sys

from PyQt5.QtWidgets import (QApplication, QLabel, QMainWindow, QStatusBar,
                             QToolBar)


class CustomMainWindow(QMainWindow):
    def __init__(self, parent=None):
        super().__init__(parent)

        self.setCentralWidget(QLabel("I'm the central widget!"))
        self.setWindowTitle('QMainWindow')

        self._create_menu()
        self._create_toolbar()
        self._create_statusbar()

    def _create_menu(self):
        menu = self.menuBar().addMenu('&Menu')
        menu.addAction('&Exit', self.close)

    def _create_toolbar(self):
        toolbar = QToolBar()
        self.addToolBar(toolbar)
        toolbar.addAction('Exit', self.close)

    def _create_statusbar(self):
        statusbar = QStatusBar()
        self.setStatusBar(statusbar)
        statusbar.showMessage("I'm the status bar")


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = CustomMainWindow()
    window.show()
    sys.exit(app.exec())
