from behave import when, then

from calculadora import soma, subtracao


@when('informar o valor de x igual {x:d}')
def quando_informar_valor_de_x(context, x):
    context.x = x


@when('informar o valor de y igual {y:d}')
def quando_informar_valor_de_y(context, y):
    context.y = y


@then('o valor retornado da soma deve ser {resultado:d}')
def entao_o_valor_retornado_da_soma_deve_ser(context, resultado):
    x = context.x
    y = context.y
    assert resultado == soma(x, y)


@then('o valor retornado da subtracao deve ser {resultado:d}')
def entao_o_valor_retornado_da_subtracao_deve_ser(context, resultado):
    x = context.x
    y = context.y
    assert resultado == subtracao(x, y)
