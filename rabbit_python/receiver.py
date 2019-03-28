import pika

import os

URL_RABBIT = os.getenv('URL_RABBIT')

connection = pika.BlockingConnection(pika.ConnectionParameters(URL_RABBIT))
channel = connection.channel()
channel.queue_declare(queue='hello')

def callback(ch, method, properties, body):
    print(f'[x] {body} recebido')


channel.basic_consume('hello',
                      callback)

print('[*] aguarndo mensagem')
channel.start_consuming()
