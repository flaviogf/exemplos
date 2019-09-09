import threading
import time
from itertools import cycle
from random import randint

from requests import get


def spin(event):
    for char in cycle('|/-\\'):
        print(char, flush=True, end='\r')
        if event.wait(1):
            break


def download_any_image():
    image = randint(200, 500)

    response = get(f'https://picsum.photos/{image}')

    with open(f'images/{image}.jpg', 'wb') as file:
        file.write(response.content)

    time.sleep(3)


def main():
    event = threading.Event()
    spin_thread = threading.Thread(target=spin, args=(event,))
    spin_thread.start()
    download_any_image()
    event.set()
    spin_thread.join()


if __name__ == '__main__':
    main()
