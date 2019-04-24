import warnings

from fabric.api import run

warnings.filterwarnings(action='ignore', module='.*paramiko.*')


def hello():
    run('echo "OK"')
