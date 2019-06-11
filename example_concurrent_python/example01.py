from concurrent.futures import ThreadPoolExecutor

from time import sleep


def slow_one():
    print('begin one')
    sleep(5)
    print('end one')
    return 5


def slow_two():
    print('begin two')
    sleep(3)
    print('end two')
    return 3


def main():
    e = ThreadPoolExecutor(4)

    a = e.submit(slow_one)
    b = e.submit(slow_two)

    return a.result() + b.result()

if __name__ == '__main__':
    print(main())
