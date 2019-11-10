import random
import sys

import pygame

pygame.init()


class Player:
    def __init__(self, size):
        self._x = 390
        self._y = 500
        self._height = 10
        self._width = 10
        self._velocity = 0
        self._size = size

    @property
    def rect(self):
        return self._x, self._y, self._height, self._width

    @property
    def color(self):
        return 255, 255, 255

    def move_rigth(self):
        self._velocity = 2

    def move_left(self):
        self._velocity = -2

    def move(self):
        width, _ = size

        if self._x < 0:
            self._x = 0
            return

        if self._x > width - self._width:
            self._x = width - self._width
            return

        self._x += self._velocity

    def stop(self):
        self._velocity = 0


class Enemy:
    def __init__(self, size):
        self._x = random.randint(0, 800)
        self._y = random.randint(0, 200)
        self._height = 20
        self._width = 20
        self._velocity = 2
        self._size = size

    @property
    def rect(self):
        return self._x, self._y, self._height, self._width

    @property
    def color(self):
        return 255, 0, 0

    def move_rigth(self):
        self._velocity = 2

    def move_left(self):
        self._velocity = -2

    def move_down(self):
        self._y += 40

    def move(self):
        width, _ = size

        if self._x <= 0:
            self.move_rigth()

        if self._x >= width:
            self.move_left()
            self.move_down()

        self._x += self._velocity

    def stop(self):
        self._velocity = 0


class Game:
    def __init__(self, player, enemies, size):
        self._display = pygame.display.set_mode(size)
        self._player = player
        self._enemies = enemies

    def render(self):
        self._display.fill((0, 0, 0))

        for enemie in self._enemies:
            self._display.fill(enemie.color, enemie)

        self._display.fill(self._player.color, self._player)

    def key_down(self, key):
        if key == pygame.K_RIGHT:
            self._player.move_rigth()
            return

        if key == pygame.K_LEFT:
            self._player.move_left()
            return

    def key_up(self, key):
        self._player.stop()

    def update(self):
        for enemie in self._enemies:

            enemie.move()

        self._player.move()

        pygame.display.update()

    def quit(self):
        sys.exit()


class Application:
    def __init__(self, game):
        self._game = game

    def main(self):
        while True:
            self._game.render()

            for event in pygame.event.get():
                if event.type == pygame.KEYDOWN:
                    self._game.key_down(event.key)

                if event.type == pygame.KEYUP:
                    self._game.key_up(event.key)

                if event.type == pygame.QUIT:
                    self._game.quit()

            self._game.update()


if __name__ == '__main__':
    size = 800, 600

    player = Player(size)

    enemies = [Enemy(size) for _ in range(4)]

    game = Game(player, enemies, size)

    application = Application(game)

    application.main()
