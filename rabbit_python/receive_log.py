import pika
import os

URL_RABBIT = os.getenv('URL_RABBIT')

connection = pika.BlockingConnection(pika.ConnectionParameters(URL_RABBIT))

channel = connection.channel()

channel.exchange_declare(exchange='logs', exchange_type='fanout')

queue = channel.queue_declare('')
queue_name = queue.method.queue

channel.queue_bind(exchange='logs', queue=queue_name)

print('[*] aguardando mensagens')

def callback(ch, method, properties, body):
    print(f'[x] {body} recebido')

channel.basic_consume(queue_name, callback, auto_ack=True)

channel.start_consuming()
