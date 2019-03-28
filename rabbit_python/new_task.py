import pika

import sys
import os

URL_RABBIT = os.getenv('URL_RABBIT')

connection = pika.BlockingConnection(pika.ConnectionParameters(URL_RABBIT))

channel = connection.channel()

channel.queue_declare(queue='tasks', durable=True)

mensagem = ' '.join(sys.argv[1:]) or 'Hello'

channel.basic_publish(exchange='',
                      routing_key='tasks',
                      body=mensagem)

print(f'[x] {mensagem} enviado')

connection.close()
