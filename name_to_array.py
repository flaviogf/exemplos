from functools import reduce

strip = lambda text: text.strip()
upper = lambda text: text.upper()
split = lambda sep: lambda text: text.split(sep)
compose = lambda *fns: lambda value: reduce(lambda previous_value, fn: fn(previous_value), fns[::-1], value)
pipe = lambda *fns: lambda value: reduce(lambda previous_value, fn: fn(previous_value), fns, value)

name = input()

print(split(' ')(upper(strip(name))))

print(compose(upper, strip)(name))

print(pipe(strip, upper)(name))
