import threading
import time

start = time.perf_counter()


def do_something(number):
    print(f'Do something {number}')
    time.sleep(1)
    print(f'Done sleep {number}')


threads = [threading.Thread(target=do_something, args=(number,))
           for number in range(10)]

for thread in threads:
    thread.start()


for thread in threads:
    thread.join()

end = time.perf_counter()

print(f'Time {round(end - start, 2)}')
