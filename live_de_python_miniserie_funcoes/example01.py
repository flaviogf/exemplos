def my_sum(x, y):
    return x + y

def my_sub(x, y):
    return x - y


def my_mul(x, y):
    return x * y


def my_div(x, y):
    return x / y



calc = {
    'sum': my_sum,
    'sub': my_sub,
    'mul': my_mul,
    'div': my_div
}


print(calc['sum'](10, 10))
print(calc['sub'](10, 10))
print(calc['mul'](10, 10))
print(calc['div'](10, 10))

