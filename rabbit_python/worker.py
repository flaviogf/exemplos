import pika
import time
import os

URL_RABBIT = os.getenv('URL_RABBIT')

connection = pika.BlockingConnection(pika.ConnectionParameters(URL_RABBIT))

channel = connection.channel()

channel.queue_declare('task', durable=True)

def callback(ch, method, propertiers, body):
    print(f'[x] {body} recebido')
    time.sleep(1)
    print(f'[x] finalizado')
    ch.basic_ack(delivery_tag=method.delivery_tag)


channel.basic_qos(prefetch_count=1)
channel.basic_consume('tasks', callback)


channel.start_consuming()
