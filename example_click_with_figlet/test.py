import click
from pyfiglet import Figlet



@click.command()
@click.option('--font', default='doom', help='name font')
@click.option('--name', prompt='your name', help='name to show')
def hello(font, name):
    f = Figlet(font=font)
    click.echo(f.renderText(f'Hello {name}'))


if __name__ == '__main__':
    hello()
