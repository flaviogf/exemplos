import random
import sys

import pygame
from pygame import mixer

pygame.init()


class GameOver(Exception):
    ...


class Player:
    def __init__(self, bullet, size):
        self._x = 390
        self._y = 500
        self._height = 10
        self._width = 10
        self._velocity = 0
        self._size = size
        self._bullet = bullet

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

        elif self._x > width - self._width:
            self._x = width - self._width

        else:
            self._x += self._velocity

            self._bullet.move(self._x)

    def shoot(self):
        self._bullet.shoot()

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

    def respawn(self):
        self._x = random.randint(0, 800)
        self._y = random.randint(0, 200)

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


class Bullet:
    def __init__(self, explosion, size):
        self._x = 390
        self._y = 500
        self._height = 5
        self._width = 5
        self._velocity = 0
        self._size = size
        self._explosion = explosion

    @property
    def rect(self):
        return self._x, self._y, self._height, self._width

    @property
    def color(self):
        return 255, 255, 0

    def is_collision(self, enemy):
        x, y, _, _ = enemy.rect

        distance_x = (self._x - x) ** 2
        distance_y = (self._y - y) ** 2
        distance = distance_x + distance_y ** (1/2)

        if distance <= 50:
            enemy.respawn()

    def shoot(self):
        self._velocity = -3
        self._explosion.play()

    def move(self, x):
        if self._y <= 0:
            self._velocity = 0
            self._y = 500

            return

        self._y += self._velocity
        self._x = x + 2.5


class Game:
    def __init__(self, player, enemies, bullet, size):
        self._display = pygame.display.set_mode(size)
        self._player = player
        self._enemies = enemies
        self._bullet = bullet

    def render(self):
        self._display.fill((0, 0, 0))

        for enemie in self._enemies:
            self._display.fill(enemie.color, enemie)

        self._display.fill(self._player.color, self._player)

        self._display.fill(self._bullet.color, self._bullet)

    def key_down(self, key):
        if key == pygame.K_RIGHT:
            self._player.move_rigth()
            return

        if key == pygame.K_LEFT:
            self._player.move_left()
            return

        if key == pygame.K_SPACE:
            self._player.shoot()
            return

    def key_up(self, key):
        self._player.stop()

    def update(self):
        enemy_close_to_player = [enemy
                                 for enemy in self._enemies
                                 if enemy.rect[1] >= 400]

        if enemy_close_to_player:
            raise GameOver("Game Over")

        for enemy in self._enemies:
            enemy.move()

            self._bullet.is_collision(enemy)

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

            try:
                self._game.update()
            except GameOver:
                break


if __name__ == '__main__':
    size = 800, 600

    explosion = mixer.Sound('explosion.wav')

    bullet = Bullet(explosion, size)

    player = Player(bullet, size)

    enemies = [Enemy(size) for _ in range(1)]

    game = Game(player, enemies, bullet, size)

    application = Application(game)

    application.main()
