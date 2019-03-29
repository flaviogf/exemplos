import pika
import os
import sys

URL_RABBIT = os.getenv('URL_RABBIT')

connection = pika.BlockingConnection(pika.ConnectionParameters(URL_RABBIT))

channel = connection.channel()

channel.exchange_declare(exchange='logs', exchange_type='fanout')

mensagem = ' '.join(sys.argv[1:]) or 'Hello World!'

channel.basic_publish(exchange='logs',
                      routing_key='',
                      body=mensagem)

print(f'[x] {mensagem} enviado')

connection.close()
