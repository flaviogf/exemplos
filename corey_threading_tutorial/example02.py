import time
from concurrent.futures import ThreadPoolExecutor, as_completed

start = time.perf_counter()


def do_something(delay):
    print(f'Do something {delay}')
    time.sleep(delay)
    return f'Done something {delay}'


with ThreadPoolExecutor() as executor:
    futures = [executor.submit(do_something, 1)
               for _ in range(10)]

    for future in as_completed(futures):
        print(future.result())


end = time.perf_counter()

print(f'End {end - start}')
